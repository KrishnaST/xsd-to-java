package com.fss.aeps.npciclient;

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

import reactor.core.publisher.Mono;

public interface NpciClient {

	public Mono<Ack> heartbeatResponse(RespHbt response);

	public Mono<Ack> paymentResponse(RespPay response);

	public Mono<Ack> nameEnquiryResponse(RespValAdd response);

	public Mono<Ack> verificationResponse(RespChkTxn response);

	public Mono<Ack> heartbeat(ReqHbt request);

	public Mono<Ack> payment(ReqPay request);

	public Mono<Ack> verification(ReqChkTxn request);

	public Mono<Ack> nameEnquiry(ReqValAdd request);

	public Mono<Ack> listAccountProviders(ReqListAccPvd request);

}