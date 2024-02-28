package com.sampleresttemplateproject.exception;

import org.springframework.http.HttpStatus;

public class ProductException {
	
	private String message;
	private Throwable throwable;
	private HttpStatus httpStatus;
	public ProductException() {
	}
	
	public ProductException(String message) {
		this.message=message;
	}
	
	public ProductException(String message, Throwable throwable, HttpStatus httpStatus) {
		this.message = message;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	@Override
	public String toString() {
		return "ProductException [message=" + message + ", throwable=" + throwable + ", httpStatus=" + httpStatus + "]";
	}
	
	
}
