package com.codeoncewidakash.exception;

public class WishlistItemNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public WishlistItemNotFoundException() {
		super();
	}
	
	public WishlistItemNotFoundException(String message) {
		super(message);
	}
}
