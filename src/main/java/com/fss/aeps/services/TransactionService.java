package com.fss.aeps.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.aeps.jaxb.HeadType;
import com.fss.aeps.jaxb.PayConstant;
import com.fss.aeps.jaxb.PayTrans;
import com.fss.aeps.jaxb.PayeeType;
import com.fss.aeps.jaxb.PayerType;
import com.fss.aeps.jaxb.ReqPay;
import com.fss.aeps.jaxb.WhiteListedConstant;
import com.fss.aeps.jpa.ImpsTransaction;
import com.fss.aeps.jpa.ImpsTransactionId;
import com.fss.aeps.jpa.ImpsTransactionPayees;
import com.fss.aeps.jpa.ImpsTransactionPayeesId;
import com.fss.aeps.repository.Repositories.TransactionRepository;
import com.fss.aeps.util.AccountDetailTagMap;
import com.fss.aeps.util.AccountIFSC;
import com.fss.aeps.util.AccountIFSCCollector;
import com.fss.aeps.util.DeviceTagMap;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	public ImpsTransaction registerTransaction(ReqPay request) {
		final HeadType head = request.getHead();
		final PayTrans txn = request.getTxn();
		final PayerType payer = request.getPayer();
		final List<PayeeType> payees = request.getPayees().getPayee();
		
		final ImpsTransaction transaction = new ImpsTransaction();
		final ImpsTransactionId transactionId = new ImpsTransactionId();
		transactionId.setTxnId(txn.getId());
		transactionId.setTxnType(txn.getType());
		transaction.setId(transactionId);
		transaction.setMsgId(head.getMsgId());
		transaction.setMsgTs(head.getTs());
		transaction.setMsgVer(head.getVer());
		transaction.setProdType(head.getProdType());
		transaction.setOrgId(head.getOrgId());
		transaction.setTxnSubType(txn.getSubType());
		transaction.setCustRef(txn.getCustRef());
		transaction.setRefId(txn.getRefId());
		transaction.setRefCategory(txn.getCategory());
		transaction.setInitiationMode(txn.getInitiationMode());
		transaction.setNote(txn.getNote());
		transaction.setPurpose(txn.getPurpose());
		transaction.setTxnTs(txn.getTs());
		transaction.setPayerAddr(payer.getAddr());
		transaction.setPayerCode(payer.getCode());
		transaction.setPayerName(payer.getName());
		transaction.setPayerSeqNum(payer.getSeqNum());
		transaction.setPayerType(payer.getType());
		transaction.setVerifiedAddr(payer.getInfo().getRating().getVerifiedAddress() == WhiteListedConstant.TRUE);
		transaction.setDeviceDetails(DeviceTagMap.toTlvString(payer.getDevice().getTag()));
		transaction.setPayerAcAddrType(payer.getAc().getAddrType());
		transaction.setPayerAcDetails(AccountDetailTagMap.toTlvString(payer.getAc().getDetail()));
		transaction.setPayerAmountCurrency(payer.getAmount().getCurr());
		transaction.setPayerAmount(payer.getAmount().getValue());
		transaction.setPayees(new ArrayList<>());
		for (int i = 0; i < payees.size(); i++) {
			ImpsTransactionPayees payee = new ImpsTransactionPayees();
			payee.setId(new ImpsTransactionPayeesId(txn.getId(), txn.getType(), payees.get(i).getSeqNum()));
			AccountIFSC accountIFSC = payees.get(i).getAc().getDetail().stream().collect(AccountIFSCCollector.getInstance());
			payee.setAcnum(accountIFSC.account);
			payee.setActype(accountIFSC.accountType);
			payee.setAmount(payees.get(i).getAmount().getValue());
			payee.setCode(payees.get(i).getCode());
			payee.setIfsc(accountIFSC.ifsc);
			payee.setType(payees.get(i).getType().name());
			transaction.getPayees().add(payee);
		}
		return transactionRepository.save(transaction);
	}
	
	public Optional<ImpsTransaction> findById(final String txnId, final PayConstant txnType) {
		return transactionRepository.findById(new ImpsTransactionId(txnId, txnType));
	}
	
	public Optional<ImpsTransaction> findTopByCustRefAndOrgIdNotOrderByMsgTsDesc(final String custRef, final String orgId){
		return transactionRepository.findTopByCustRefAndOrgIdNotOrderByMsgTsDesc(custRef, orgId);
	}
	
	public Optional<ImpsTransaction> findTopByIdTxnIdAndOrgIdOrderByMsgTsDesc(final String txnId, final String orgId){
		return transactionRepository.findTopByIdTxnIdAndOrgIdOrderByMsgTsDesc(txnId, orgId);
	}

	public ImpsTransaction update(ImpsTransaction impsTransaction) {
		return transactionRepository.save(impsTransaction);
	}
	
}
