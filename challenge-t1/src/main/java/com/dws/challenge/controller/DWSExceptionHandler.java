package com.dws.challenge.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dws.challenge.exceptions.AccountNotFoundException;

@ControllerAdvice  
@RestController  
public class DWSExceptionHandler {
	public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler  
	{  
		
		
	@ExceptionHandler(Exception.class)  
	public final ResponseEntity<Object> handleAllExcep(Exception ex, WebRequest request)  
	{  
		AccountNotFoundException exceptionResponse= new AccountNotFoundException(ex.getMessage());  
	return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);  
	}
	
	@ExceptionHandler(AccountNotFoundException.class)  
	public final ResponseEntity<Object> accountNotFoundException(AccountNotFoundException ex, WebRequest request)  
	{  
		AccountNotFoundException exceptionResponse= new AccountNotFoundException(ex.getMessage());  
	return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND); 
	}
	
	@Override  
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)   
	{  
		AccountNotFoundException exceptionResponse= new AccountNotFoundException(ex.getMessage());  
	return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST); 
	} 
	}
	
}
