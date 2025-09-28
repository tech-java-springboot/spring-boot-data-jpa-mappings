package com.codeoncewidakash.exception;

public class TVChannelNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TVChannelNotFoundException() {
		super();
	}
	
	public TVChannelNotFoundException(String message) {
		super(message);
	}

}
