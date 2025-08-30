package com.codeoncewidakash.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewidakash.exception.CustomerNotFoundException;
import com.codeoncewidakash.exception.WishlistItemNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(exception = CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException cnfe){
		return new ResponseEntity<>(cnfe.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception = WishlistItemNotFoundException.class)
	public ResponseEntity<String> handleWishlistItemNotFoundException(WishlistItemNotFoundException wnf){
		return new ResponseEntity<>(wnf.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
