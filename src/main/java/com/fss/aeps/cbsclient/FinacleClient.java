package com.fss.aeps.cbsclient;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.fss.aeps.AppConfig;
import com.fss.aeps.cbsclient.model.CBSResponse;
import com.fss.aeps.cbsclient.model.IMPSTransactionRequest;
import com.fss.aeps.cbsclient.model.IMPSTransactionResponse;
import com.fss.aeps.cbsclient.model.NameEnquiryRequest;
import com.fss.aeps.cbsclient.model.NameEnquiryResponse;
import com.fss.aeps.jaxb.AccountDetailType;
import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.ReqHbt;
import com.fss.aeps.jaxb.RespHbt;
import com.fss.aeps.jpa.ImpsTransaction;
import com.fss.aeps.jpa.ImpsTransactionPayees;
import com.fss.aeps.npciclient.RetroLoggingInterceptor;
import com.fss.aeps.npciclient.UnsafeOkHttp;
import com.fss.aeps.util.AccountDetailTagMap;
import com.fss.aeps.util.Mapper;

import okhttp3.OkHttpClient;
import reactor.core.publisher.Mono;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

@Lazy
@Component("finacle")
public class FinacleClient implements CBSClient {

	private static final Logger logger = LoggerFactory.getLogger(FinacleClient.class);

	private Retrofit retrofit;

	@Autowired
	@Qualifier("cbsToNpciResponseMapper")
	private Mapper finacleToNpciResponseMapper;
	
	public interface CBSService {

		@POST("InwardImpsTransaction")
		Call<IMPSTransactionResponse> credit(@Body IMPSTransactionRequest request);
		
		@POST("impsBeneficiaryVerification")
		Call<IMPSTransactionResponse> verification(@Body IMPSTransactionRequest request);
		
		@POST("impsacctvalidation")
		Call<NameEnquiryResponse> nameEnquiry(@Body NameEnquiryRequest request);
	}
	

	public FinacleClient(@Autowired AppConfig appConfig) {
		final OkHttpClient.Builder builder = UnsafeOkHttp.Builder();
		builder.addInterceptor(new RetroLoggingInterceptor(RetroLoggingInterceptor.Level.BODY));
		builder.connectTimeout(appConfig.cbsConnectTimeout, TimeUnit.MILLISECONDS);
		builder.readTimeout(appConfig.cbsReadTimeout, TimeUnit.MILLISECONDS);
		retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(JacksonConverterFactory.create()).baseUrl(appConfig.cbsBaseUrl).build();
	}

	public final Ack sendHeartbeat(final ReqHbt hbt) throws IOException {
		final NpciImpsService service = retrofit.create(NpciImpsService.class);
		final Call<Ack> call = service.heartbeat(hbt.getHead().getVer(), hbt.getTxn().getId(), hbt);
		final Response<Ack> response = call.execute();
		return response.body();
	}

	public final Ack sendHeartbeatResponse(final RespHbt hbt) throws IOException {
		final NpciImpsService service = retrofit.create(NpciImpsService.class);
		final Call<Ack> call = service.heartbeatResponse(hbt.getHead().getVer(), hbt.getTxn().getId(), hbt);
		final Response<Ack> response = call.execute();
		return response.body();
	}

	@Override
	public Mono<CBSResponse> credit(ImpsTransaction transaction) {
		try {
			logger.info("processing transaction at core banking.");
			final ImpsTransactionPayees payee = transaction.getPayees().get(0);

			final IMPSTransactionRequest request = new IMPSTransactionRequest();

			request.stan = transaction.getRefId();
			request.transType = "P2A";
			request.benfIFSC = payee.getIfsc();
			request.accountNo = payee.getAcnum();
			request.benfAccNo = payee.getAcnum();
			request.ifscCode = payee.getIfsc();
			request.remitterName = transaction.getPayerName();
			request.narration = transaction.getNote();
			request.remitterAccNo = payee.getAcnum();

			final Map<String, String> payerAccountDetails = AccountDetailTagMap.toTagMap(transaction.getPayerAcDetails());
			request.remitterMMID = payerAccountDetails.get(AccountDetailType.MMID.name());
			request.remitterMobile = payerAccountDetails.get(AccountDetailType.MOBNUM.name());
			request.RRNNo = transaction.getCustRef();
			request.transAmt = payee.getAmount().doubleValue();

			final CBSService service = retrofit.create(CBSService.class);
			final Call<IMPSTransactionResponse> call = service.credit(request);
			IMPSTransactionResponse impsTransactionResponse = call.execute().body();
			return Mono.just(new CBSResponse(finacleToNpciResponseMapper.map(impsTransactionResponse.errorCode), impsTransactionResponse.errorMessage));
		} catch (Exception e) {
			logger.error("error processing credit at CBS.", e);
		}
		return null;

	}
	
	public Mono<CBSResponse> verify(ImpsTransaction transaction) {
		try {
			logger.info("processing transaction at core banking.");
			final ImpsTransactionPayees payee = transaction.getPayees().get(0);

			final IMPSTransactionRequest request = new IMPSTransactionRequest();

			request.stan = transaction.getRefId();
			request.transType = "P2A";
			request.benfIFSC = payee.getIfsc();
			request.accountNo = payee.getAcnum();
			request.benfAccNo = payee.getAcnum();
			request.ifscCode = payee.getIfsc();
			request.remitterName = transaction.getPayerName();
			request.narration = transaction.getNote();
			request.remitterAccNo = payee.getAcnum();

			final Map<String, String> payerAccountDetails = AccountDetailTagMap.toTagMap(transaction.getPayerAcDetails());
			request.remitterMMID = payerAccountDetails.get(AccountDetailType.MMID.name());
			request.remitterMobile = payerAccountDetails.get(AccountDetailType.MOBNUM.name());
			request.RRNNo = transaction.getCustRef();
			request.transAmt = payee.getAmount().doubleValue();

			final CBSService service = retrofit.create(CBSService.class);
			final Call<IMPSTransactionResponse> call = service.verification(request);
			IMPSTransactionResponse impsTransactionResponse = call.execute().body();
			return Mono.just(new CBSResponse(finacleToNpciResponseMapper.map(impsTransactionResponse.errorCode), impsTransactionResponse.errorMessage));
		} catch (Exception e) {
			logger.error("error processing credit at CBS.", e);
		}
		return null;
	}

	@Override
	public Mono<NameEnquiryResponse> nameEnquiry(NameEnquiryRequest nameEnquiryRequest) {
		try {
			final CBSService service = retrofit.create(CBSService.class);
			final Call<NameEnquiryResponse> call = service.nameEnquiry(nameEnquiryRequest);
			NameEnquiryResponse nameEnquiryResponse = call.execute().body();
			return Mono.just(new NameEnquiryResponse(finacleToNpciResponseMapper.map(nameEnquiryResponse.responseCode), nameEnquiryResponse.responseMessage,  nameEnquiryResponse.payeeName));
		} catch (Exception e) {
			logger.error("error processing credit at CBS.", e);
		}
		return null;
		
	}
}
