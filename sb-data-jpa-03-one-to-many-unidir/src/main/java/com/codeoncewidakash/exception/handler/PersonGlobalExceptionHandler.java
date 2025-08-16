package com.codeoncewidakash.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewidakash.exception.CardDetailsNotFoundException;
import com.codeoncewidakash.exception.PersonNotFoundException;

@RestControllerAdvice
public class PersonGlobalExceptionHandler {
	
	@ExceptionHandler(exception = PersonNotFoundException.class)
	public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException pnfe){
		return new ResponseEntity<>(pnfe.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception = CardDetailsNotFoundException.class)
	public ResponseEntity<String> handleCardDetailsNotFoundException(CardDetailsNotFoundException cdnfe){
		return new ResponseEntity<>(cdnfe.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<String> handleOtherExceptions(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
