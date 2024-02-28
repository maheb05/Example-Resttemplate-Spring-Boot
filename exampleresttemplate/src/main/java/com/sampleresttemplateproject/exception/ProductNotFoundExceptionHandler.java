package com.sampleresttemplateproject.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductNotFoundExceptionHandler {
	
	@ExceptionHandler(value = {ProductNotFoundException.class})
	public ResponseEntity<Object> productNotFoundExceptionHandler(ProductNotFoundException productNotFoundException){
			Map<String,Object> body = new HashMap<>();
			body.put("message", productNotFoundException.getMessage());
			return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExceptin(Exception ex){
		Map<String,Object> body = new HashMap<>();
		body.put("message","An exception occured, no products found with that id");
		return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
