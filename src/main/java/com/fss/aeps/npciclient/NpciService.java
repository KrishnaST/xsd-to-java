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

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NpciService {
	
	@Headers({"Accept: application/xml"})
	@POST("ReqHbt/{version}/urn:txnid:{id}")
	public Call<Ack> heartbeat(@Path("version") String version, @Path("id") final String txnid, @Body final ReqHbt request);
	
	@Headers({"Accept: application/xml"})
	@POST("ReqPay/{version}/urn:txnid:{id}")
	public Call<Ack> paymentRequest(@Path("version") String version, @Path("id") final String txnid, @Body final ReqPay request);

	@Headers({"Accept: application/xml"})
	@POST("ReqChkTxn/{version}/urn:txnid:{id}")
	public Call<Ack> verificationRequest(@Path("version") String version, @Path("id") final String txnid, @Body final ReqChkTxn request);
	
	@Headers({"Accept: application/xml"})
	@POST("ReqValAdd/{version}/urn:txnid:{id}")
	public Call<Ack> nameEnquiry(@Path("version") String version, @Path("id") final String txnid, @Body final ReqValAdd request);
	
	@Headers({"Accept: application/xml"})
	@POST("ReqListAccPvd/{version}/urn:txnid:{id}")
	public Call<Ack> listAccountProviders(@Path("version") String version, @Path("id") final String txnid, @Body final ReqListAccPvd request);
	
	
	@Headers({"Accept: application/xml"})
	@POST("RespHbt/{version}/urn:txnid:{id}")
	public Call<Ack> heartbeatResponse(@Path("version") String version, @Path("id") final String txnid, @Body final RespHbt response);

	@Headers({"Accept: application/xml"})
	@POST("RespPay/{version}/urn:txnid:{id}")
	public Call<Ack> paymentResponse(@Path("version") String version, @Path("id") final String txnid, @Body final RespPay response);
	
	@Headers({"Accept: application/xml"})
	@POST("RespChkTxn/{version}/urn:txnid:{id}")
	public Call<Ack> verificationResponse(@Path("version") String version, @Path("id") final String txnid, @Body final RespChkTxn response);
	
	@Headers({"Accept: application/xml"})
	@POST("RespValAdd/{version}/urn:txnid:{id}")
	public Call<Ack> nameEnquiryResponse(@Path("version") String version, @Path("id") final String txnid, @Body final RespValAdd response);

}