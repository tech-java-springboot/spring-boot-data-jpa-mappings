package com.codeoncewidakash.exception;

public class FileNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileNotExistException() {
		super();
	}
	
	public FileNotExistException(String message) {
		super(message);
	}
}
