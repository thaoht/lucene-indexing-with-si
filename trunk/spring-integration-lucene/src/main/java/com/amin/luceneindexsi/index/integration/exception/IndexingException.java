package com.amin.luceneindexsi.index.integration.exception;

public class IndexingException {
	
	public final Object payLoad;
	
	public final Throwable exceptionThrown;

	public IndexingException(Throwable exceptionThrown, Object payLoad) {
		super();
		this.exceptionThrown = exceptionThrown;
		this.payLoad = payLoad;
	}
	
	public Object getPayLoad() {
		return payLoad;
	}

	public Throwable getExceptionThrown() {
		return exceptionThrown;
	}
}
	
