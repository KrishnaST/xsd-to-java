package com.fss.aeps.cbsclient;

import com.fss.aeps.cbsclient.model.CBSResponse;
import com.fss.aeps.cbsclient.model.NameEnquiryRequest;
import com.fss.aeps.cbsclient.model.NameEnquiryResponse;
import com.fss.aeps.jpa.ImpsTransaction;

import reactor.core.publisher.Mono;

public interface CBSClient {
	
	public Mono<CBSResponse> credit(final ImpsTransaction transaction);
	
	public Mono<CBSResponse> verify(final ImpsTransaction transaction);
	
	public Mono<NameEnquiryResponse> nameEnquiry(final NameEnquiryRequest request);

}
