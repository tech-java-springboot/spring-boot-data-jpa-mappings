package com.codeoncewidakash.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewidakash.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class EmployeeGlobalExceptionHandler {
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotException(EmployeeNotFoundException enfe){
		return new ResponseEntity<String>(enfe.getMessage(), HttpStatus.NOT_FOUND);
	}
}
