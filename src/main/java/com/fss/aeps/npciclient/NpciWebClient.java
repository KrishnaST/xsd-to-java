package com.fss.aeps.npciclient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fss.aeps.AppConfig;
import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.ReqChkTxn;
import com.fss.aeps.jaxb.ReqHbt;
import com.fss.aeps.jaxb.ReqListAccPvd;
import com.fss.aeps.jaxb.ReqPay;
import com.fss.aeps.jaxb.ReqValAdd;
import com.fss.aeps.jaxb.RespChkTxn;
import com.fss.aeps.jaxb.RespHbt;
import com.fss.aeps.jaxb.RespPay;
import com.fss.aeps.jaxb.RespValAdd;

import okhttp3.OkHttpClient;
import reactor.core.publisher.Mono;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NpciWebClient implements NpciClient {

	private Retrofit npciClient;
	
	public NpciWebClient(@Autowired AppConfig appConfig, @Autowired ClientSignatureInterceptor clientSignatureInterceptor) {
		final OkHttpClient.Builder builder = UnsafeOkHttp.Builder();
		builder.addInterceptor(new RetroLoggingInterceptor(RetroLoggingInterceptor.Level.BODY));
		builder.addInterceptor(clientSignatureInterceptor);
		builder.connectTimeout(appConfig.npciConnectTimeout, TimeUnit.MILLISECONDS);
		builder.readTimeout(appConfig.npciReadTimeout, TimeUnit.MILLISECONDS);
		npciClient = new Retrofit.Builder().client(builder.build()).addConverterFactory(JaxbConverterFactory.create()).baseUrl(appConfig.npciBaseUrl).build();
	}

	
	@Override
	public final Mono<Ack> heartbeatResponse(final RespHbt response) {
		final NpciService service = npciClient.create(NpciService.class);
		final Call<Ack> call = service.heartbeatResponse(response.getHead().getVer(), response.getTxn().getId(), response);
		return Mono.just(getSuppressedResponse(call));
	}
	
	
	@Override
	public final Mono<Ack> paymentResponse(final RespPay response) {
		final NpciService service = npciClient.create(NpciService.class);
		final Call<Ack> call = service.paymentResponse(response.getHead().getVer(), response.getTxn().getId(), response);
		return Mono.just(getSuppressedResponse(call));
	}
	
	
	@Override
	public final Mono<Ack> nameEnquiryResponse(final RespValAdd response) {
		final NpciService service = npciClient.create(NpciService.class);
		final Call<Ack> call = service.nameEnquiryResponse(response.getHead().getVer(), response.getTxn().getId(), response);
		return Mono.just(getSuppressedResponse(call));
	}
	
	
	@Override
	public final Mono<Ack> verificationResponse(final RespChkTxn response) {
		final NpciService service = npciClient.create(NpciService.class);
		final Call<Ack> call = service.verificationResponse(response.getHead().getVer(), response.getTxn().getId(), response);
		return Mono.just(getSuppressedResponse(call));
	}
	
	
	@Override
	public final Mono<Ack> heartbeat(final ReqHbt request) {
		final NpciService service = npciClient.create(NpciService.class);
		final Call<Ack> call = service.heartbeat(request.getHead().getVer(), request.getTxn().getId(), request);
		return Mono.just(getSuppressedResponse(call));
	}

	
	@Override
	public final Mono<Ack> payment(final ReqPay request) {
		final NpciService service = npciClient.create(NpciService.class);
		final Call<Ack> call = service.paymentRequest(request.getHead().getVer(), request.getTxn().getId(), request);
		return Mono.just(getSuppressedResponse(call));
	}
	
	
	@Override
	public final Mono<Ack> verification(final ReqChkTxn request) {
		final NpciService service = npciClient.create(NpciService.class);
		final Call<Ack> call = service.verificationRequest(request.getHead().getVer(), request.getTxn().getId(), request);
		return Mono.just(getSuppressedResponse(call));
	}

	
	@Override
	public final Mono<Ack> nameEnquiry(final ReqValAdd request) {
		final NpciService service = npciClient.create(NpciService.class);
		final Call<Ack> call = service.nameEnquiry(request.getHead().getVer(), request.getTxn().getId(), request);
		return Mono.just(getSuppressedResponse(call));
	}
	
	
	@Override
	public final Mono<Ack> listAccountProviders(final ReqListAccPvd request) {
		final NpciService service = npciClient.create(NpciService.class);
		final Call<Ack> call = service.listAccountProviders(request.getHead().getVer(), request.getTxn().getId(), request);
		return Mono.just(getSuppressedResponse(call));
	}

	
	private static <T> T getSuppressedResponse(final Call<T> call) {
		try {
			return call.execute().body();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
