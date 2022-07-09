package com.fss.aeps.cbsclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class IMPSTransactionRequest {

	public String remitterMobile;
	public String remitterMMID;
	public String remitterAccNo;
	public String benfAccNo;	
	public String benfIFSC;
	public Double transAmt;
	public String operator;
	public String narration;
	public String RRNNo;	
	public String benfMMID;
	public String transType;
	public String benfMobile;	
	public String accountNo;
	public String ifscCode;
	public String stan;
	public String terminalID;
	public String acquirerId;
	public String remitterName;
	
}
