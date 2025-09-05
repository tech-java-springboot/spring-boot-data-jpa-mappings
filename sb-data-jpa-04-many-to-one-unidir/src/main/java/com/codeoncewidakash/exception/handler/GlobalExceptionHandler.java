package com.codeoncewidakash.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewidakash.exception.DepartmentNotFoundException;
import com.codeoncewidakash.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = DepartmentNotFoundException.class)
	public ResponseEntity<String> handleDepartmentNotFoundException(DepartmentNotFoundException dnfe){
		return new ResponseEntity<>(dnfe.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException enfe){
		return new ResponseEntity<>(enfe.getMessage(), HttpStatus.NOT_FOUND);
	}
}
