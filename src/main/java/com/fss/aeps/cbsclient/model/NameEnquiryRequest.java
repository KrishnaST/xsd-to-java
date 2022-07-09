package com.fss.aeps.cbsclient.model;

import java.math.BigDecimal;

public class NameEnquiryRequest {

	public String payeeAccount;
	public String payeeIfsc;
	public String payeeAccountType;
	public String txnId;
	public String custRef;
	public String note;
	public String payerDeviceDetails;
	public String payerDetails;
	public String txntype;
	public BigDecimal amount;

	@Override
	public String toString() {
		return "NameEnquiryRequest [payeeAccount=" + payeeAccount + ", payeeIfsc=" + payeeIfsc + ", payeeAccountType=" + payeeAccountType + ", txnId=" + txnId + ", custRef="
				+ custRef + ", note=" + note + ", payerDeviceDetails=" + payerDeviceDetails + ", payerDetails=" + payerDetails + ", txntype=" + txntype + ", amount=" + amount
				+ "]";
	}

}
