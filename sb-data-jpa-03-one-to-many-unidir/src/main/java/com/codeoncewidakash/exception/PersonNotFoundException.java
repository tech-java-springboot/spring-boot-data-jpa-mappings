package com.codeoncewidakash.exception;

public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PersonNotFoundException() {
		super();
	}
	
	public PersonNotFoundException(String message) {
		super(message);
	}
}
