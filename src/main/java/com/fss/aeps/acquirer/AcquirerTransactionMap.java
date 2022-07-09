package com.fss.aeps.acquirer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fss.aeps.jaxb.RespChkTxn;
import com.fss.aeps.jaxb.RespPay;
import com.fss.aeps.jaxb.RespValAdd;

public class AcquirerTransactionMap {

	public static final Map<String, AcquirerTransaction<RespPay>> 		paymentTransactions = new ConcurrentHashMap<>();
	public static final Map<String, AcquirerTransaction<RespChkTxn>> 	verificationTransactions = new ConcurrentHashMap<>();
	public static final Map<String, AcquirerTransaction<RespValAdd>> 	nameEnquiryTransactions = new ConcurrentHashMap<>();

}
