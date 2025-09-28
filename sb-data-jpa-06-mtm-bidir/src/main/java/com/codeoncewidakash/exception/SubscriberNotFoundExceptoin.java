package com.codeoncewidakash.exception;

public class SubscriberNotFoundExceptoin extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public SubscriberNotFoundExceptoin() {
		super();
	}
	
	public SubscriberNotFoundExceptoin(String message) {
		super(message);
	}
}
