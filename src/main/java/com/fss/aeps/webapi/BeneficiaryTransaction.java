package com.fss.aeps.webapi;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fss.aeps.cbsclient.CBSClient;
import com.fss.aeps.cbsclient.model.CBSResponse;
import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.HeadType;
import com.fss.aeps.jaxb.ListedAccountType;
import com.fss.aeps.jaxb.PayeeType;
import com.fss.aeps.jaxb.Ref;
import com.fss.aeps.jaxb.RefType;
import com.fss.aeps.jaxb.ReqPay;
import com.fss.aeps.jaxb.RespPay;
import com.fss.aeps.jaxb.ResultType;
import com.fss.aeps.jaxb.RespPay.Resp;
import com.fss.aeps.jpa.ImpsTransaction;
import com.fss.aeps.npciclient.NpciClient;
import com.fss.aeps.services.TransactionService;
import com.fss.aeps.util.AccountIFSC;
import com.fss.aeps.util.AccountIFSCCollector;

import reactor.core.publisher.Mono;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BeneficiaryTransaction extends IssuerTransaction<ReqPay, RespPay> {

	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryTransaction.class);

	@Autowired
	private ApplicationContext context;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private NpciClient npciWebClient;

	@Autowired
	@Qualifier("cbsClient")
	private CBSClient cbsClient;

	public BeneficiaryTransaction(ReqPay request, RespPay response) {
		super(request, response);
	}

	@Override
	public final void run() {
		try {
			logger.info("******* executing " + request.getTxn().getId());
			final ImpsTransaction transaction = transactionService.registerTransaction(request);
			final List<PayeeType> payees = request.getPayees().getPayee();

			final HeadType head = context.getBean(HeadType.class);

			response.setHead(head);
			response.setTxn(request.getTxn());

			final Mono<CBSResponse> mono = cbsClient.credit(transaction);
			if(mono == null) logger.error("response not received from cbs for txn : "+request.getTxn().getId());
			else mono.subscribe(cbsResponse -> {
				final Resp resp = new Resp();
				resp.setReqMsgId(request.getHead().getMsgId());
				if ("00".equals(cbsResponse.responseCode))
					resp.setResult(ResultType.SUCCESS);
				else {
					resp.setResult(ResultType.FAILURE);
					resp.setErrCode(cbsResponse.responseCode);
				}
				response.setResp(resp);

				for (int i = 0; i < payees.size(); i++) {
					AccountIFSC accountIFSC = payees.get(i).getAc().getDetail().stream().collect(AccountIFSCCollector.getInstance());
					final PayeeType payee = payees.get(i);
					final Ref payeeRef = new Ref();
					payeeRef.setType(RefType.PAYEE);
					payeeRef.setSeqNum(payee.getSeqNum());
					payeeRef.setAddr(accountIFSC.account + "@" + accountIFSC.ifsc + ".ifsc.npci");
					if (resp.getResult() == ResultType.SUCCESS) {
						payeeRef.setSettAmount(payee.getAmount().getValue());
						payeeRef.setRespCode("00");
						payeeRef.setApprovalNum("123456");
					} else {
						payeeRef.setSettAmount(new BigDecimal(0));
						payeeRef.setRespCode(resp.getErrCode());
					}
					payeeRef.setSettCurrency(payee.getAmount().getCurr());
					payeeRef.setAcNum(accountIFSC.account);
					payeeRef.setIFSC(accountIFSC.ifsc);
					payeeRef.setCode(payee.getCode());
					payeeRef.setAccType(ListedAccountType.valueOf(accountIFSC.accountType));
					resp.getRef().add(payeeRef);
				}

				
				transaction.setRespErrorCode(response.getResp().getErrCode());
				transaction.setRespMsgId(response.getHead().getMsgId());
				transaction.setRespMsgTs(response.getHead().getTs());
				transaction.setRespResult(response.getResp().getResult());
				response.getResp().getRef().stream().findFirst().ifPresent(ref -> {
					transaction.setRespApprovalNo(ref.getApprovalNum());
					transaction.setRespCode(ref.getRespCode());
				});
				transactionService.update(transaction);
				
				logger.info("sending response of beneficiary transaction");
				final Mono<Ack> monoAck = npciWebClient.paymentResponse(response);
				monoAck.subscribe(ack -> {
					logger.info("response of beneficiary transaction sent." + ack);
				});
			});
			
		} catch (Exception e) {
			logger.error("error accured in beneficiary transaction : " + request.getTxn().getId(), e);
		}
	}

}
