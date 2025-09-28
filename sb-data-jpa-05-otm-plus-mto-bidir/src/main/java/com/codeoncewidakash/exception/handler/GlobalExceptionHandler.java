package com.codeoncewidakash.exception.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewidakash.exception.PolicyNotFoundException;
import com.codeoncewidakash.payload.ErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = PolicyNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePolicyNotFoundException(PolicyNotFoundException pnfe){
		return new ResponseEntity<>(new ErrorResponse(pnfe.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}
}
