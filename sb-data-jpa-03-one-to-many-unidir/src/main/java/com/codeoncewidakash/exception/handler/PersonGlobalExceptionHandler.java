package com.codeoncewidakash.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewidakash.exception.PersonNotFoundException;

@RestControllerAdvice
public class PersonGlobalExceptionHandler {
	
	@ExceptionHandler(exception = PersonNotFoundException.class)
	public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException pnfe){
		return new ResponseEntity<String>(pnfe.getMessage(), HttpStatus.NOT_FOUND);
	}
}
