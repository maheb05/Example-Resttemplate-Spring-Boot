package com.sampleresttemplateproject.exception;

public class ProductNotFoundException extends RuntimeException {
	
	private final String errorCode;
	
	public ProductNotFoundException(String message,String notFound) {
		super(message);
		this.errorCode = notFound;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
}
