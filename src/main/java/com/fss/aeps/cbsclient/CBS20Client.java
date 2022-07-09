package com.fss.aeps.cbsclient;

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
import com.fss.aeps.jpa.ImpsTransaction;
import com.fss.aeps.jpa.ImpsTransactionPayees;
import com.fss.aeps.npciclient.RetroLoggingInterceptor;
import com.fss.aeps.npciclient.UnsafeOkHttp;
import com.fss.aeps.util.AccountDetailTagMap;
import com.fss.aeps.util.Mapper;

import okhttp3.OkHttpClient;
import reactor.core.publisher.Mono;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

@Lazy
@Component("2.0")
public class CBS20Client implements CBSClient {

	private static final Logger logger = LoggerFactory.getLogger(CBS20Client.class);

	private Retrofit retrofit;

	@Autowired
	@Qualifier("cbsToNpciResponseMapper")
	private Mapper cbsToNpciResponseMapper;

	public interface CBSService {

		@POST("impsCreditTransaction")
		Call<IMPSTransactionResponse> credit(@Body IMPSTransactionRequest request);
		
		@POST("impsBeneficiaryVerification")
		Call<IMPSTransactionResponse> verification(@Body IMPSTransactionRequest request);

		@POST("impsacctvalidation")
		Call<NameEnquiryResponse> nameEnquiry(@Body NameEnquiryRequest request);
	}

	public CBS20Client(@Autowired AppConfig appConfig) {
		final OkHttpClient.Builder builder = UnsafeOkHttp.Builder();
		builder.addInterceptor(new RetroLoggingInterceptor(RetroLoggingInterceptor.Level.BODY));
		builder.connectTimeout(appConfig.cbsConnectTimeout, TimeUnit.MILLISECONDS);
		builder.readTimeout(appConfig.cbsReadTimeout, TimeUnit.MILLISECONDS);
		retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(JacksonConverterFactory.create()).baseUrl(appConfig.cbsBaseUrl).build();
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
			return Mono.just(new CBSResponse(cbsToNpciResponseMapper.map(impsTransactionResponse.errorCode), impsTransactionResponse.errorMessage));
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
			return Mono.just(new CBSResponse(cbsToNpciResponseMapper.map(impsTransactionResponse.errorCode), impsTransactionResponse.errorMessage));
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
			return Mono.just(new NameEnquiryResponse(cbsToNpciResponseMapper.map(nameEnquiryResponse.responseCode), nameEnquiryResponse.responseMessage, nameEnquiryResponse.payeeName));
		} catch (Exception e) {
			logger.error("error processing credit at CBS.", e);
		}
		return null;
		
	}

}
