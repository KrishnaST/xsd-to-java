package com.fss.aeps.todo;

import org.springframework.context.annotation.Configuration;


@Configuration
public class NpciWebClientBuilder {

	public String npciBaseUrl = "https://127.0.0.1/acquirer/";


	/*
	 * @Bean public WebClient getWebClient() throws SSLException { SslContext
	 * sslContext = SslContextBuilder.forClient()
	 * .trustManager(InsecureTrustManagerFactory.INSTANCE) .build(); return
	 * WebClient.builder() .baseUrl(npciBaseUrl) .clientConnector(new
	 * ReactorClientHttpConnector(HttpClient.create().secure(t ->
	 * t.sslContext(sslContext)))) .defaultHeader(HttpHeaders.ACCEPT,
	 * MediaType.APPLICATION_XML_VALUE) .defaultHeader(HttpHeaders.CONTENT_TYPE,
	 * MediaType.APPLICATION_XML_VALUE) .build(); }
	 * 
	 * public ExchangeFilterFunction requestSignatureInterceptor = (request,
	 * nextFilter) -> { HttpMethod httpMethod = request.method(); if (httpMethod ==
	 * HttpMethod.POST) { }
	 * 
	 * return nextFilter.exchange(request); };
	 */
	
	
}