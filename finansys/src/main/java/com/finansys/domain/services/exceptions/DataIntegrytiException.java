package com.finansys.domain.services.exceptions;

public class DataIntegrytiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegrytiException (String msg) {
		super(msg);
	}
	
	public DataIntegrytiException (String msg, Throwable cause) {
		super(msg,cause);
	}
	
	

}
