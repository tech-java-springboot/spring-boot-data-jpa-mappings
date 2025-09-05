package com.codeoncewidakash.exception;

public class RoleNotExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RoleNotExistException() {
		super();
	}

	public RoleNotExistException(String message) {
		super(message);
	}
}
