package com.codeoncewidakash.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredJwtTokenException extends AuthenticationException {
	private static final long serialVersionUID = 1L;
	
	public ExpiredJwtTokenException(String message) {
		super(message);
	}
	
	public ExpiredJwtTokenException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
