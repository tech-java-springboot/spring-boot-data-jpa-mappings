package com.codeoncewidakash.exception.handler;

import javax.management.relation.RoleInfoNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewidakash.exception.CustomerNotFoundException;
import com.codeoncewidakash.exception.ExpiredJwtTokenException;
import com.codeoncewidakash.exception.UserNotExistException;
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
	
	@ExceptionHandler(exception = RoleInfoNotFoundException.class)
	public ResponseEntity<String> handleRoleInfoNotFoundException(RoleInfoNotFoundException rnf){
		return new ResponseEntity<>(rnf.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception = UserNotExistException.class)
	public ResponseEntity<String> handleUserNotExistException(UserNotExistException unf){
		return new ResponseEntity<>(unf.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	/*@ExceptionHandler(exception = JwtTokenExpiredException.class)
	public ResponseEntity<String> handleJwtTokenExpiredException(JwtTokenExpiredException jtee){
		return new ResponseEntity<>(jtee.getMessage(), HttpStatus.NOT_FOUND);
	}*/
}
