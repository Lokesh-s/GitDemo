package com.utility.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations=RestController.class)
public class WebControllerAdvice {
	
	@ExceptionHandler({ImproperDataException.class})
	public ResponseEntity<ExceptionResponse> specialException(ImproperDataException dataException) {
		ExceptionResponse exceptionResponse=new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionResponse.setMessage(dataException.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({NumberFormatException.class})
	public ResponseEntity<ExceptionResponse> numberFormatException(NumberFormatException dataException) {
		ExceptionResponse exceptionResponse=new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionResponse.setMessage("Please enter a integer number");
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({NotTriangleException.class})
	public ResponseEntity<ExceptionResponse> notTriangleException(NotTriangleException dataException) {
		ExceptionResponse exceptionResponse=new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionResponse.setMessage(dataException.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler({EmptyArrayException.class})
	public ResponseEntity<ExceptionResponse> emptyArrayException(EmptyArrayException dataException) {
		ExceptionResponse exceptionResponse=new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionResponse.setMessage(dataException.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
