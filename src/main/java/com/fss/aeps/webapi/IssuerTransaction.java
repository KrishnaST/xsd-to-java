package com.fss.aeps.webapi;

public abstract class IssuerTransaction<R, S> implements Runnable {

	protected final R request;
	protected final S response;
	
	public IssuerTransaction(R request, S response) {
		this.request = request;
		this.response = response;
	}
	public R getRequest() {
		return request;
	}
	public S getResponse() {
		return response;
	}
	
}
