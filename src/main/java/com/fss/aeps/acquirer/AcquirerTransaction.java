package com.fss.aeps.acquirer;

public interface AcquirerTransaction<T> extends Runnable {

	public void processResponse(T response);
}
