package com.fss.aeps.webapi;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fss.aeps.cbsclient.CBSClient;
import com.fss.aeps.cbsclient.model.NameEnquiryRequest;
import com.fss.aeps.cbsclient.model.NameEnquiryResponse;
import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.HeadType;
import com.fss.aeps.jaxb.ListedAccountType;
import com.fss.aeps.jaxb.ReqValAdd;
import com.fss.aeps.jaxb.RespTypeValAddr;
import com.fss.aeps.jaxb.RespValAdd;
import com.fss.aeps.jaxb.ResultType;
import com.fss.aeps.npciclient.NpciClient;
import com.fss.aeps.util.AccountDetailTagMap;
import com.fss.aeps.util.AccountIFSC;
import com.fss.aeps.util.AccountIFSCCollector;
import com.fss.aeps.util.DeviceTagMap;

import reactor.core.publisher.Mono;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BeneficiaryNameEnquiry extends IssuerTransaction<ReqValAdd, RespValAdd> {

	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryNameEnquiry.class);
	
	@Autowired
	ApplicationContext context;
	
	
	@Autowired
	private NpciClient npciWebClient;
	
	@Autowired
	@Qualifier("cbsClient")
	private CBSClient cbsClient;
	
	public BeneficiaryNameEnquiry(ReqValAdd request, RespValAdd response) {
		super(request, response);
	}

	@Override
	public final void run() {
		try {
			logger.info("******* executing "+request.getTxn().getId());
			final HeadType head = context.getBean(HeadType.class);
			
			response.setHead(head);
			response.setTxn(request.getTxn());
			
			AccountIFSC accountIfsc = request.getPayee().getAc().getDetail().stream().collect(AccountIFSCCollector.getInstance());
			
			final NameEnquiryRequest nameEnquiryRequest = new NameEnquiryRequest();
			nameEnquiryRequest.custRef = request.getTxn().getCustRef();
			nameEnquiryRequest.txnId = request.getTxn().getId();
			nameEnquiryRequest.payeeAccount = accountIfsc.account;
			nameEnquiryRequest.payeeIfsc = accountIfsc.ifsc;
			nameEnquiryRequest.payeeAccountType = accountIfsc.accountType;
			nameEnquiryRequest.payerDetails = AccountDetailTagMap.toTlvString(request.getPayer().getAc().getDetail());
			nameEnquiryRequest.payerDeviceDetails = DeviceTagMap.toTlvString(request.getPayer().getDevice().getTag());
			nameEnquiryRequest.note = request.getTxn().getNote();
			nameEnquiryRequest.txntype = "CR";
			nameEnquiryRequest.amount=(new BigDecimal(0));
			
			logger.info("nameEnquiryRequest : \r\n"+new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(nameEnquiryRequest));
			
			final Mono<NameEnquiryResponse> nameEnquiryResponseMono = cbsClient.nameEnquiry(nameEnquiryRequest);
			if(nameEnquiryResponseMono == null) logger.error("response not received from cbs for name enquiry : "+request.getTxn().getId());
			else nameEnquiryResponseMono.subscribe(nameResponse -> {
				final RespTypeValAddr resp = new RespTypeValAddr();
				resp.setReqMsgId(request.getHead().getMsgId());
				response.setResp(resp);
				
				if("00".equals(nameResponse.responseCode)) {
					resp.setResult(ResultType.SUCCESS);
					resp.setReqMsgId(request.getHead().getMsgId());
					resp.setType(request.getPayee().getType());
					resp.setCode(request.getPayee().getCode());
					
					resp.setAcNum(accountIfsc.account);
					resp.setIFSC(accountIfsc.ifsc);
					resp.setAccType(ListedAccountType.valueOf(accountIfsc.accountType));
					resp.setMaskName(nameResponse.payeeName);
					resp.setApprovalNum("123456");
				}
				else {
					resp.setResult(ResultType.FAILURE);
					resp.setErrCode(nameResponse.responseCode);
					resp.setReqMsgId(request.getHead().getMsgId());
					resp.setType(request.getPayee().getType());
					resp.setCode(request.getPayee().getCode());
					resp.setAcNum(accountIfsc.account);
					resp.setIFSC(accountIfsc.ifsc);
					resp.setAccType(ListedAccountType.valueOf(accountIfsc.accountType));
				}
				logger.info("sending response of beneficiary transaction");
				final Mono<Ack> mono = npciWebClient.nameEnquiryResponse(response);
				mono.subscribe(ack -> {
					logger.info("response of beneficiary transaction sent." + ack);
				});
			});
			
			
		} catch (Exception e) {logger.error("error accured in beneficiary transaction : "+request.getTxn().getId(), e);}
	}

}
