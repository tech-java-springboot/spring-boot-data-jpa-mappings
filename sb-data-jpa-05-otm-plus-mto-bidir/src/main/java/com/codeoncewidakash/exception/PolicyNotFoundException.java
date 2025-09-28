package com.codeoncewidakash.exception;

public class PolicyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PolicyNotFoundException() {
		super();
	}

	public PolicyNotFoundException(String message) {
		super(message);
	}
}
