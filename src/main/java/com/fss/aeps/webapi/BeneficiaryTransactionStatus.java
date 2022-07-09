package com.fss.aeps.webapi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fss.aeps.ResponseCode;
import com.fss.aeps.cbsclient.CBSClient;
import com.fss.aeps.cbsclient.model.CBSResponse;
import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.HeadType;
import com.fss.aeps.jaxb.ListedAccountType;
import com.fss.aeps.jaxb.PayConstant;
import com.fss.aeps.jaxb.Ref;
import com.fss.aeps.jaxb.RefType;
import com.fss.aeps.jaxb.ReqChkTxn;
import com.fss.aeps.jaxb.RespChkTxn;
import com.fss.aeps.jaxb.ResultType;
import com.fss.aeps.jaxb.RespChkTxn.Resp;
import com.fss.aeps.jpa.ImpsTransaction;
import com.fss.aeps.jpa.ImpsTransactionPayees;
import com.fss.aeps.npciclient.NpciClient;
import com.fss.aeps.services.TransactionService;

import reactor.core.publisher.Mono;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BeneficiaryTransactionStatus extends IssuerTransaction<ReqChkTxn, RespChkTxn> {

	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryTransactionStatus.class);

	@Autowired
	private ApplicationContext context;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private NpciClient npciWebClient;

	@Autowired
	@Qualifier("cbsClient")
	private CBSClient cbsClient;

	public BeneficiaryTransactionStatus(ReqChkTxn request, RespChkTxn response) {
		super(request, response);
	}

	@Override
	public final void run() {
		try {
			logger.info("******* executing " + request.getTxn().getId());
			final Optional<ImpsTransaction> transaction = transactionService.findById(request.getTxn().getOrgTxnId(), PayConstant.CREDIT);

			final HeadType head = context.getBean(HeadType.class);

			response.setHead(head);
			response.setTxn(request.getTxn());

			final Resp resp = new Resp();
			resp.setReqMsgId(request.getHead().getMsgId());
			resp.setResult(ResultType.FAILURE);
			response.setResp(resp);
			resp.setErrCode(ResponseCode.NO_RESPONSE);

			if (transaction.isEmpty()) {
				logger.info("sending response of beneficiary transaction as original not found.");
				final Mono<Ack> mono = npciWebClient.verificationResponse(response);
				mono.subscribe(ack -> {
					logger.info("response of beneficiary transaction sent." + ack);
				});
				return;
			}

			final ImpsTransaction impsTransaction = transaction.get();

			final Mono<CBSResponse> mono = cbsClient.verify(impsTransaction);
			if(mono == null) logger.error("response not received from cbs for txn status : "+request.getTxn().getId());
			else mono.subscribe(cbsResponse -> {
				logger.info("cbsResponse : " + cbsResponse);
				if ("00".equals(cbsResponse.responseCode)) {
					resp.setResult(ResultType.SUCCESS);
					resp.setErrCode(null);
				} else {
					resp.setResult(ResultType.FAILURE);
					resp.setErrCode(cbsResponse.responseCode);
				}

				final List<ImpsTransactionPayees> payees = impsTransaction.getPayees();
				for (int i = 0; i < payees.size(); i++) {
					final ImpsTransactionPayees payee = payees.get(i);
					final Ref payeeRef = new Ref();
					payeeRef.setType(RefType.PAYEE);
					payeeRef.setSeqNum(payee.getId().getSeqNum());
					payeeRef.setAddr(payee.getAcnum() + "@" + payee.getIfsc() + ".ifsc.npci");
					if (resp.getResult() == ResultType.SUCCESS) {
						payeeRef.setSettAmount(payee.getAmount());
						payeeRef.setApprovalNum("123456");
						payeeRef.setRespCode(ResponseCode.SUCCESS);
					} else {
						payeeRef.setSettAmount(new BigDecimal(0));
						payeeRef.setRespCode(resp.getErrCode());
					}
					payeeRef.setSettCurrency(impsTransaction.getPayerAmountCurrency());
					payeeRef.setAcNum(payee.getAcnum());
					payeeRef.setIFSC(payee.getIfsc());
					payeeRef.setCode(payee.getCode());
					payeeRef.setAccType(ListedAccountType.valueOf(payee.getActype()));
					resp.getRef().add(payeeRef);
				}

				if (impsTransaction.getRespResult() != ResultType.SUCCESS) {
					impsTransaction.setRespErrorCode(response.getResp().getErrCode());
					impsTransaction.setRespMsgId(response.getHead().getMsgId());
					impsTransaction.setRespMsgTs(response.getHead().getTs());
					impsTransaction.setRespResult(response.getResp().getResult());
					response.getResp().getRef().stream().findFirst().ifPresent(ref -> {
						impsTransaction.setRespApprovalNo(ref.getApprovalNum());
						impsTransaction.setRespCode(ref.getRespCode());
					});
					transactionService.update(impsTransaction);
				}

				logger.info("sending response of beneficiary transaction");
				final Mono<Ack> monoAck = npciWebClient.verificationResponse(response);
				monoAck.subscribe(ack -> logger.info("response of beneficiary transaction sent." + ack));
			});

		} catch (Exception e) {
			logger.error("error accured in beneficiary transaction : " + request.getTxn().getId(), e);
		}
	}

}
