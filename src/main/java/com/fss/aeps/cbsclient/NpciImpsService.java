package com.fss.aeps.cbsclient;

import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.ReqHbt;
import com.fss.aeps.jaxb.RespHbt;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NpciImpsService {

	@POST("RespHbt/{version}/urn:txnid:{id}")
	public Call<Ack> heartbeatResponse(@Path("version") String version, @Path("id") final String txnid, @Body final RespHbt hbt);

	@POST("ReqHbt/{version}/urn:txnid:{id}")
	public Call<Ack> heartbeat(@Path("version") String version, @Path("id") final String txnid, @Body final ReqHbt hbt);

}