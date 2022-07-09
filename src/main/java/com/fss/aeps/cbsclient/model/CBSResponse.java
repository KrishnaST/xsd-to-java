package com.fss.aeps.cbsclient.model;

public class CBSResponse {

	public String responseCode;
	public String responseMessage;
	public String authCode;

	public CBSResponse() {
	}

	public CBSResponse(String responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public CBSResponse(String responseCode, String responseMessage, String authCode) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "[responseCode=" + responseCode + ", responseMessage=" + responseMessage + ", authCode=" + authCode + "]";
	}

	
}
