package com.codeoncewidakash.exception;

public class CardDetailsNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CardDetailsNotFoundException() {
		super();
	}

	public CardDetailsNotFoundException(String message) {
		super(message);
	}
}
