package com.fss.aeps.acquirer.iso8583;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.util.iso8583.EncoderDecoder;
import org.util.iso8583.ISO8583Message;
import org.util.iso8583.api.ISOFormat;
import org.util.iso8583.format.CBSFormat;

import com.fss.aeps.AppConfig;
import com.fss.aeps.acquirer.AcquirerTransaction;
import com.fss.aeps.acquirer.AcquirerTransactionMap;
import com.fss.aeps.jaxb.AccountDetailType;
import com.fss.aeps.jaxb.AccountType;
import com.fss.aeps.jaxb.Ack;
import com.fss.aeps.jaxb.AddressType;
import com.fss.aeps.jaxb.AmountType;
import com.fss.aeps.jaxb.DeviceTagNameType;
import com.fss.aeps.jaxb.DeviceType;
import com.fss.aeps.jaxb.HeadType;
import com.fss.aeps.jaxb.IdentityConstant;
import com.fss.aeps.jaxb.IdentityType;
import com.fss.aeps.jaxb.InfoType;
import com.fss.aeps.jaxb.PayConstant;
import com.fss.aeps.jaxb.PayTrans;
import com.fss.aeps.jaxb.PayeeType;
import com.fss.aeps.jaxb.PayerConstant;
import com.fss.aeps.jaxb.PayerType;
import com.fss.aeps.jaxb.RatingType;
import com.fss.aeps.jaxb.RefType;
import com.fss.aeps.jaxb.ReqChkTxn;
import com.fss.aeps.jaxb.RespChkTxn;
import com.fss.aeps.jaxb.ResultType;
import com.fss.aeps.jaxb.TxnSubType;
import com.fss.aeps.jaxb.WhiteListedConstant;
import com.fss.aeps.jaxb.AccountType.Detail;
import com.fss.aeps.jaxb.DeviceType.Tag;
import com.fss.aeps.jpa.ImpsTransaction;
import com.fss.aeps.npciclient.NpciClient;
import com.fss.aeps.services.TransactionService;
import com.fss.aeps.util.Generator;
import com.fss.aeps.util.Mapper;
import com.fss.aeps.util.Tlv;

import reactor.core.publisher.Mono;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class ISO8583Verification implements AcquirerTransaction<RespChkTxn>  {

	private static final Logger logger = LoggerFactory.getLogger(ISO8583AcquirerServer.class);
	private static final ISOFormat cbsFormat = CBSFormat.getInstance();

	private Socket socket;
	private ISO8583Message isoRequest;
	
	public ISO8583Verification(Socket socket, ISO8583Message isoRequest) {
		this.socket = socket;
		this.isoRequest = isoRequest;
	}
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	private AppConfig appConfig;
	
	@Autowired
	private NpciClient npciWebClient ;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	@Qualifier("npciToAcquirerResponseMapper")
	private Mapper npciToAcquirerResponseMapper;
	
	
	@Override
	public final void run() {
		try {
			Optional<ImpsTransaction> impsTransaction = transactionService.findTopByCustRefAndOrgIdNotOrderByMsgTsDesc(isoRequest.get(37), "NPCI");
			if(impsTransaction.isEmpty()) {
				logger.info("original transaction not found for rrn : "+isoRequest.get(37));
				processResponse("91");
				return;
			}
			
			final ImpsTransaction transaction = impsTransaction.get();
			
			final Tlv tlv = Tlv.parse(isoRequest.get(120));
			final ReqChkTxn request = new ReqChkTxn();
			final HeadType head = context.getBean(HeadType.class);
			final PayTrans txn = new PayTrans();
			final PayerType payer = new PayerType();
			final PayeeType payee = new PayeeType();
			final AccountType payeeAccount = new AccountType();
			
			final InfoType info = new InfoType();
			final IdentityType identity = new IdentityType();
			final RatingType rating = new RatingType();
			
			final AccountType payerAccount = new AccountType();
			final AmountType amount = new AmountType();
			
			request.setHead(head);
			request.setTxn(txn);
			request.setPayer(payer);
			request.setPayee(payee);
			payer.setInfo(info);
			
			txn.setId(Generator.newRandomTxnId(appConfig.participationCode));
			final String remarks = tlv.get("051");
			txn.setNote((remarks == null || remarks.length() == 0) ? "Note not sent by middleware" : remarks); 
			txn.setRefId(isoRequest.get(11));
			txn.setCustRef(isoRequest.get(37));
			txn.setRefUrl("https://www.npci.org.in/");
			txn.setTs(new Date());
			txn.setType(PayConstant.CHK_BANK_STATUS);
			txn.setInitiationMode("00");
			txn.setSubType(TxnSubType.PAY);
			txn.setPurpose("00");
			txn.setRefCategory("00");
			
			txn.setOrgTxnId(transaction.getId().getTxnId());
			txn.setOrgTxnDate(transaction.getTxnTs());
			txn.setOrgRrn(transaction.getCustRef());
			
			payer.setAddr(appConfig.orgId+"@"+appConfig.participationCode);
			payer.setName(tlv.get("045").trim());
			payer.setSeqNum(1);
			if("0000".equals(isoRequest.get(18))) {
				payer.setType(PayerConstant.PERSON);
				payer.setCode(isoRequest.get(18));
			}
			else {
				payer.setType(PayerConstant.ENTITY);
				payer.setCode(isoRequest.get(18));
			}
			
			payer.setInfo(info);
			info.setIdentity(identity);
			info.setRating(rating);
			//identity.setId("002141400005154|DMKJ0000001");
			identity.setId(isoRequest.get(102)+"|DMKJ0000002");
			identity.setType(IdentityConstant.ACCOUNT);
			String varifyNm = tlv.get("045").trim();
			varifyNm = varifyNm.replaceAll("[^a-zA-Z0-9]", " ");  
			identity.setVerifiedName(varifyNm);
			//identity.setVerifiedName(tlv.get("045").trim());
			rating.setVerifiedAddress(WhiteListedConstant.TRUE);
			
			final String mmidMobile = tlv.get("050");
			final String mobile = mmidMobile.substring(7).length() == 10 ? "91" + mmidMobile.substring(7) : mmidMobile.substring(7);
			
			payer.setDevice(new DeviceType());
			payer.getDevice().getTag().add(new Tag(DeviceTagNameType.TYPE, tlv.get("002")));  //MOB APP REQ
			payer.getDevice().getTag().add(new Tag(DeviceTagNameType.MOBILE, mobile)); // mmidMobile.substring(7)
			payer.getDevice().getTag().add(new Tag(DeviceTagNameType.LOCATION, isoRequest.get(43)));
			payer.getDevice().getTag().add(new Tag(DeviceTagNameType.CARD_ACCP_TR_ID, appConfig.participationCode + mobile.substring(mobile.length()-5)));
			payer.getDevice().getTag().add(new Tag(DeviceTagNameType.CARD_ACC_ID_CODE, appConfig.participationCode + mobile));
			
			payer.setAc(payerAccount);
			payerAccount.setAddrType(AddressType.MOBILE);
			payerAccount.getDetail().add(new Detail(AccountDetailType.ACTYPE, "SAVINGS")); 
			payerAccount.getDetail().add(new Detail(AccountDetailType.MOBNUM, mobile));
			payerAccount.getDetail().add(new Detail(AccountDetailType.MMID, appConfig.bankmmid)); //mmidMobile.substring(0,7)
			
			payer.setAmount(amount);
			amount.setCurr("INR");
			amount.setValue(new BigDecimal(Double.parseDouble(isoRequest.get(4))/100.0));
			
			payee.setSeqNum(1);
			payee.setType(PayerConstant.PERSON);
			payee.setCode("0000");
			payee.setAc(payeeAccount);
			payeeAccount.setAddrType(AddressType.ACCOUNT);
			payeeAccount.getDetail().add(new Detail(AccountDetailType.ACTYPE, "SAVINGS")); //get specific if possible
			payeeAccount.getDetail().add(new Detail(AccountDetailType.ACNUM, tlv.get("062")));
			payeeAccount.getDetail().add(new Detail(AccountDetailType.IFSC, tlv.get("059")));
			payee.setAmount(amount);
			
			AcquirerTransactionMap.verificationTransactions.put(request.getTxn().getType().name()+request.getTxn().getId(), this);
			
			final Mono<Ack> mono = npciWebClient.verification(request);
			mono.subscribe(ack -> {
				logger.info("response of beneficiary transaction sent." + ack);
			});
		
			
		} 
		catch (Exception e) {
			logger.error("error converting iso8583 message to xml message", e);
		}
	}
	

	@Override
	public void processResponse(RespChkTxn response) {
		try(OutputStream os = socket.getOutputStream()) {
			final Optional<ImpsTransaction> transaction = transactionService.findTopByIdTxnIdAndOrgIdOrderByMsgTsDesc(response.getTxn().getOrgTxnId(), appConfig.orgId);
			if(transaction.isPresent()) {
				ImpsTransaction impsTransaction = transaction.get();
				impsTransaction.setRespErrorCode(response.getResp().getErrCode());
				impsTransaction.setRespMsgId(response.getHead().getMsgId());
				impsTransaction.setRespMsgTs(response.getHead().getTs());
				impsTransaction.setRespResult(response.getResp().getResult());
				response.getResp().getRef().stream().filter(f -> f.getType() == RefType.PAYEE).findFirst().ifPresent(ref -> {
					impsTransaction.setRespApprovalNo(ref.getApprovalNum());
					impsTransaction.setRespCode(ref.getRespCode());
				});
				transactionService.update(impsTransaction);
			}
			
			final ISO8583Message isoResponse = isoRequest.clone();
			
			if(response.getResp().getResult() == ResultType.SUCCESS) {
				isoResponse.put(39, "00");
			}else if(response.getResp().getResult() == ResultType.FAILURE) {
				isoResponse.put(39, npciToAcquirerResponseMapper.map(response.getResp().getErrCode()));
			}else {
				isoResponse.put(39, npciToAcquirerResponseMapper.map(response.getResp().getErrCode()));
			}
			final byte[] responseBytes = EncoderDecoder.encode(cbsFormat, isoResponse);
			os.write(responseBytes);
			os.flush();
			logger.info("response sent back to acquirer.");
		} catch (IOException e) {
			logger.error("error accured sending response back to acquirer", e);
		}
	}
	
	public void processResponse(String responseCode) {
		try(OutputStream os = socket.getOutputStream()) {
			final ISO8583Message isoResponse = isoRequest;
			isoResponse.put(39, responseCode);
			final byte[] responseBytes = EncoderDecoder.encode(cbsFormat, isoResponse);
			os.write(responseBytes);
			os.flush();
			logger.info("response sent back to acquirer.");
		} catch (IOException e) {
			logger.error("error accured sending response back to acquirer", e);
		}
	}

}