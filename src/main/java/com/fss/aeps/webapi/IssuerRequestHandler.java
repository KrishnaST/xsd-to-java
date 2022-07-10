package com.fss.aeps.webapi;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fss.aeps.acquirer.AcquirerTransaction;
import com.fss.aeps.acquirer.AcquirerTransactionMap;
import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.HeadType;
import com.fss.aeps.jaxb.ReqChkTxn;
import com.fss.aeps.jaxb.ReqHbt;
import com.fss.aeps.jaxb.ReqPay;
import com.fss.aeps.jaxb.ReqValAdd;
import com.fss.aeps.jaxb.RespChkTxn;
import com.fss.aeps.jaxb.RespHbt;
import com.fss.aeps.jaxb.RespListAccPvd;
import com.fss.aeps.jaxb.RespPay;
import com.fss.aeps.jaxb.RespType;
import com.fss.aeps.jaxb.RespValAdd;
import com.fss.aeps.jaxb.ResultType;
import com.fss.aeps.npciclient.NpciClient;

import reactor.core.publisher.Mono;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
@RequestMapping("aeps")
public class IssuerRequestHandler {

	private static final Logger logger = LoggerFactory.getLogger(IssuerRequestHandler.class);
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	private NpciClient npciWebClient;
	
	@Autowired
	@Qualifier(value = "threadpool")
	private ThreadPoolExecutor executor;
	
	@PostMapping(path = "/ReqHbt/{version}/urn:txnid:{txnid}", produces = MediaType.APPLICATION_XML_VALUE)
	public Ack heartbeat(@RequestBody ReqHbt request, @PathVariable("version") String version, @PathVariable("txnid") String txnid) {
		
		final Ack ack = new Ack();
		ack.setReqMsgId(request.getHead().getMsgId());
		ack.setTs(new Date());
		ack.setApi(request.getClass().getSimpleName());
		executor.execute(() -> {
			try {
				final RespHbt response = new RespHbt();
				final HeadType head = context.getBean(HeadType.class);
				
				final RespType resp = new RespType();
				resp.setReqMsgId(request.getHead().getMsgId());
				resp.setResult(ResultType.SUCCESS);

				response.setHead(head);
				response.setResp(resp);
				response.setTxn(request.getTxn());
				
				final Mono<Ack> mono = npciWebClient.heartbeatResponse(response);
				mono.subscribe(a -> {
					logger.info("response of beneficiary transaction sent." + a);
				});
			} catch (Exception e) {logger.error(e.getMessage(), e);}
		});
		return ack;
	}
	
	@PostMapping(path = "/ReqPay/{version}/urn:txnid:{txnid}", produces = MediaType.APPLICATION_XML_VALUE)
	public Ack pay(@RequestBody ReqPay request, @PathVariable("version") String version, @PathVariable("txnid") String txnid) {
		final Ack ack = new Ack();
		ack.setReqMsgId(request.getHead().getMsgId());
		ack.setTs(new Date());
		ack.setApi(request.getClass().getSimpleName());
		BeneficiaryTransaction transaction = context.getBean(BeneficiaryTransaction.class, request, new RespPay());
		executor.execute(transaction);
		return ack;
	}
	
	@PostMapping(path = "/ReqChkTxn/{version}/urn:txnid:{txnid}", produces = MediaType.APPLICATION_XML_VALUE)
	public Ack verify(@RequestBody ReqChkTxn reqPay, @PathVariable("version") String version, @PathVariable("txnid") String txnid) {
		final Ack ack = new Ack();
		ack.setReqMsgId(reqPay.getHead().getMsgId());
		ack.setTs(new Date());
		ack.setApi("ReqChkTxn");
		final BeneficiaryTransactionStatus transaction = context.getBean(BeneficiaryTransactionStatus.class, reqPay, new RespChkTxn());
		executor.execute(transaction);
		return ack;
	}
	
	@PostMapping(path = "/ReqValAdd/{version}/urn:txnid:{txnid}", produces = MediaType.APPLICATION_XML_VALUE)
	public Ack nameEnquiry(@RequestBody ReqValAdd request, @PathVariable("version") String version, @PathVariable("txnid") String txnid) {
		final Ack ack = new Ack();
		ack.setReqMsgId(request.getHead().getMsgId());
		ack.setTs(new Date());
		ack.setApi(request.getClass().getSimpleName());
		BeneficiaryNameEnquiry transaction = context.getBean(BeneficiaryNameEnquiry.class, request, new RespValAdd());
		executor.execute(transaction);
		return ack;
	}
	
	@PostMapping(path = "/RespHbt/{version}/urn:txnid:{txnid}", produces = MediaType.APPLICATION_XML_VALUE)
	public Ack heartbeatResponse(@RequestBody RespHbt response, @PathVariable("version") String version, @PathVariable("txnid") String txnid) {
		logger.info("********** Response Received : "+response.getTxn().getId());
		final Ack ack = new Ack();
		ack.setReqMsgId(response.getHead().getMsgId());
		ack.setTs(new Date());
		ack.setApi(response.getClass().getSimpleName());
		return ack;
	}
	
	@PostMapping(path = "/RespPay/{version}/urn:txnid:{txnid}", produces = MediaType.APPLICATION_XML_VALUE)
	public Ack pay(@RequestBody RespPay response, @PathVariable("version") String version, @PathVariable("txnid") String txnid) {
		final Ack ack = new Ack();
		ack.setReqMsgId(response.getHead().getMsgId());
		ack.setTs(new Date());
		ack.setApi(response.getClass().getSimpleName());
		logger.info("response received for transaction : "+response.getTxn().getId());
		final AcquirerTransaction<RespPay> transaction = AcquirerTransactionMap.paymentTransactions.remove(response.getTxn().getType().name()+response.getTxn().getId());
		if(transaction == null) throw new RuntimeException("no iso8583 transaction found for response from npci.");
		transaction.processResponse(response);
		return ack;
	}
	
	@PostMapping(path = "/RespChkTxn/{version}/urn:txnid:{txnid}", produces = MediaType.APPLICATION_XML_VALUE)
	public Ack verificationResponse(@RequestBody RespChkTxn response, @PathVariable("version") String version, @PathVariable("txnid") String txnid) {
		final Ack ack = new Ack();
		ack.setReqMsgId(response.getHead().getMsgId());
		ack.setTs(new Date());
		ack.setApi(response.getClass().getSimpleName());
		logger.info("response received for transaction : "+response.getTxn().getId());
		final AcquirerTransaction<RespChkTxn> transaction = AcquirerTransactionMap.verificationTransactions.remove(response.getTxn().getType().name()+response.getTxn().getId());
		if(transaction == null) throw new RuntimeException("no iso8583 transaction found for response from npci.");
		transaction.processResponse(response);
		return ack;
	}
	
	@PostMapping(path = "/RespValAdd/{version}/urn:txnid:{txnid}", produces = MediaType.APPLICATION_XML_VALUE)
	public Ack nameEnquiryResponse(@RequestBody RespValAdd response, @PathVariable("version") String version, @PathVariable("txnid") String txnid) {
		final Ack ack = new Ack();
		ack.setReqMsgId(response.getHead().getMsgId());
		ack.setTs(new Date());
		ack.setApi(response.getClass().getSimpleName());
		logger.info("response received for transaction : "+response.getTxn().getId());
		final AcquirerTransaction<RespValAdd> transaction = AcquirerTransactionMap.nameEnquiryTransactions.remove(response.getTxn().getType().name()+response.getTxn().getId());
		if(transaction == null) throw new RuntimeException("no iso8583 transaction found for response from npci.");
		transaction.processResponse(response);
		return ack;
	}
	
	@PostMapping(path = "/RespListAccPvd/{version}/urn:txnid:{txnid}", produces = MediaType.APPLICATION_XML_VALUE)
	public Ack nameEnquiryResponse(@RequestBody RespListAccPvd response, @PathVariable("version") String version, @PathVariable("txnid") String txnid) {
		final Ack ack = new Ack();
		ack.setReqMsgId(response.getHead().getMsgId());
		ack.setTs(new Date());
		ack.setApi(response.getClass().getSimpleName());
		logger.info("response received for transaction : "+response.getTxn().getId());
		//final AcquirerTransaction<RespValAdd> transaction = AcquirerTransactionMap.nameEnquiryTransactions.remove(response.getTxn().getType().name()+response.getTxn().getId());
	//	if(transaction == null) throw new RuntimeException("no iso8583 transaction found for response from npci.");
		//transaction.processResponse(response);
		return ack;
	}
}
