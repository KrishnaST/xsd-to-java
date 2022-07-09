package com.fss.aeps.cbsclient.model;

public class NameEnquiryResponse {

	public String responseCode;
	public String responseMessage;
	public String payeeName;
	public String txnId;
	
	
	public NameEnquiryResponse() {
	}
	
	
	public NameEnquiryResponse(String responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public NameEnquiryResponse(String responseCode, String responseMessage, String payeeName) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.payeeName = payeeName;
	}


	@Override
	public String toString() {
		return "NameEnquiryResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage + ", payeeName=" + payeeName + ", txnId=" + txnId + "]";
	}

}
