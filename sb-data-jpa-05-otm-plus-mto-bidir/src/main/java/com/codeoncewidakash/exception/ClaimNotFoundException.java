package com.codeoncewidakash.exception;

public class ClaimNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ClaimNotFoundException() {
		super();
	}

	public ClaimNotFoundException(String message) {
		super(message);
	}
}
