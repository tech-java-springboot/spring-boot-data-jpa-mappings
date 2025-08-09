package com.codeoncewidakash.exception.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewidakash.exception.FileNotExistException;

@RestControllerAdvice
public class FileStoreGlobalExceptionHandler {
	
	@ExceptionHandler(value = IOException.class)
	public ResponseEntity<String> handlerIOException(IOException ioe){
		return new ResponseEntity<String>(ioe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = FileNotExistException.class)
	public ResponseEntity<String> handlerIOException(FileNotExistException fne){
		return new ResponseEntity<String>(fne.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handlerIOException(Exception e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
