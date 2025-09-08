package com.codeoncewidakash.exception;

import org.springframework.security.core.AuthenticationException;

public class AccessJwtTokenExpiredException extends AuthenticationException {
	private static final long serialVersionUID = 1L;
	
	public AccessJwtTokenExpiredException(String message) {
		super(message);
	}
	
	public AccessJwtTokenExpiredException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
