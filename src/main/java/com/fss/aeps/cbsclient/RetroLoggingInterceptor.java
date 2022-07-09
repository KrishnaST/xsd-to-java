
package com.fss.aeps.cbsclient;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;

public final class RetroLoggingInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(RetroLoggingInterceptor.class);

	private static final Charset UTF8 = Charset.forName("UTF-8");
	private volatile Level level = Level.BASIC;

	public enum Level {
		NONE, BASIC, HEADERS, BODY
	}

	//@formatter:off
	public RetroLoggingInterceptor() {}

	public RetroLoggingInterceptor(final Level level) {
		this.level = level;
	}

	private volatile Set<String> headersToRedact = Collections.emptySet();

	public final void redactHeader(String name) {
		Set<String> newHeadersToRedact = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		newHeadersToRedact.addAll(headersToRedact);
		newHeadersToRedact.add(name);
		headersToRedact = newHeadersToRedact;
	}

	public RetroLoggingInterceptor setLevel(Level level) {
		if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
		this.level = level;
		return this;
	}

	public final Level getLevel() {
		return level;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		final Level level = this.level;

		Request request = chain.request();
		if (level == Level.NONE) { return chain.proceed(request); }

		boolean logBody    = level == Level.BODY;
		boolean logHeaders = logBody || level == Level.HEADERS;

		RequestBody requestBody    = request.body();
		boolean     hasRequestBody = requestBody != null;

		Connection connection          = chain.connection();
		String     requestStartMessage = "--> " + request.method() + ' ' + request.url() + (connection != null ? " " + connection.protocol() : "");
		if (!logHeaders && hasRequestBody) { requestStartMessage += " (" + requestBody.contentLength() + "-byte body)"; }
		logger.trace(requestStartMessage);

		if (logHeaders) {
			if (hasRequestBody) {

				if (requestBody.contentType() != null) { logger.trace("Content-Type: " + requestBody.contentType()); }
				if (requestBody.contentLength() != -1) { logger.trace("Content-Length: " + requestBody.contentLength()); }
			}

			logHeader(logger, request.headers(), " --> ");

			if (!logBody || !hasRequestBody) {
				logger.trace("--> END " + request.method());
			} else if (bodyHasUnknownEncoding(request.headers())) {
				logger.trace("--> END " + request.method() + " (encoded body omitted)");
			} else if (requestBody.isDuplex()) {
				logger.trace("--> END " + request.method() + " (duplex request body omitted)");
			} else {
				Buffer buffer = new Buffer();
				requestBody.writeTo(buffer);

				Charset   charset     = UTF8;
				MediaType contentType = requestBody.contentType();
				if (contentType != null) { charset = contentType.charset(UTF8); }

				logger.trace("");
				if (isPlaintext(buffer)) {
					logger.trace(buffer.readString(charset));
					logger.trace("--> END " + request.method() + " (" + requestBody.contentLength() + "-byte body)");
				} else {
					logger.trace("--> END " + request.method() + " (binary " + requestBody.contentLength() + "-byte body omitted)");
				}
			}
		}

		long     startNs = System.nanoTime();
		Response response;
		try {
			response = chain.proceed(request);
		} catch (Exception e) {
			logger.trace("<-- HTTP FAILED: " + e);
			throw e;
		}
		long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

		ResponseBody responseBody  = response.body();
		long         contentLength = responseBody.contentLength();
		String       bodySize      = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
		logger.trace("<-- " + response.code() + (response.message().isEmpty() ? "" : ' ' + response.message()) + ' ' + response.request().url() + " (" + tookMs
				+ "ms" + (!logHeaders ? ", " + bodySize + " body" : "") + ')');

		if (logHeaders) {
			final Headers headers = response.headers();
			logHeader(logger, headers, " <-- ");
			if (!logBody || !HttpHeaders.promisesBody(response)) {
				logger.trace("<-- END HTTP");
			} else if (bodyHasUnknownEncoding(response.headers())) {
				logger.trace("<-- END HTTP (encoded body omitted)");
			} else {
				BufferedSource source = responseBody.source();
				source.request(Long.MAX_VALUE);
				Buffer buffer = source.getBuffer();

				Long gzippedLength = null;
				if ("gzip".equalsIgnoreCase(headers.get("Content-Encoding"))) {
					gzippedLength = buffer.size();
					try(GzipSource gzippedResponseBody = new GzipSource(buffer.clone())) {
						buffer = new Buffer();
						buffer.writeAll(gzippedResponseBody);
					}
				}

				Charset   charset     = UTF8;
				MediaType contentType = responseBody.contentType();
				if (contentType != null) { charset = contentType.charset(UTF8); }

				if (!isPlaintext(buffer)) {
					logger.trace("");
					logger.trace("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
					return response;
				}

				if (contentLength != 0) {
					logger.trace("");
					logger.trace(buffer.clone().readString(charset));
				}

				if (gzippedLength != null) {
					logger.trace("<-- END HTTP (" + buffer.size() + "-byte, " + gzippedLength + "-gzipped-byte body)");
				} else {
					logger.trace("<-- END HTTP (" + buffer.size() + "-byte body)");
				}
			}
		}

		return response;
	}

	private final void logHeader(final Logger logger, final Headers headers, final String direction) {
		final Map<String, List<String>> map = headers.toMultimap();
		final StringBuilder sb = new StringBuilder("headers").append(direction);
		logger.trace(writeHeaders(map, sb).toString());
	}
	
	private final static StringBuilder writeHeaders(final Map<String, List<String>> map, final StringBuilder sb) {
		if (map.isEmpty()) return sb.append("{}");
		sb.append("{");
		final Set<Entry<String, List<String>>> set   = map.entrySet();
		final int                              size  = set.size();
		int                                    count = 0;
		for (final Entry<String, List<String>> e : set) {
			count++;
			sb.append(e.getKey());
			sb.append('=');
			sb.append(e.getValue());
			if (count != size) sb.append(", ");
		}
		sb.append("}");
		return sb;
	}

	private static final boolean isPlaintext(Buffer buffer) {
		try {
			Buffer prefix    = new Buffer();
			long   byteCount = buffer.size() < 64 ? buffer.size() : 64;
			buffer.copyTo(prefix, 0, byteCount);
			for (int i = 0; i < 16; i++) {
				if (prefix.exhausted()) { break; }
				int codePoint = prefix.readUtf8CodePoint();
				if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) { return false; }
			}
			return true;
		} catch (EOFException e) {
			return false; // Truncated UTF-8 sequence.
		}
	}

	private static final boolean bodyHasUnknownEncoding(Headers headers) {
		String contentEncoding = headers.get("Content-Encoding");
		return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity") && !contentEncoding.equalsIgnoreCase("gzip");
	}
}
