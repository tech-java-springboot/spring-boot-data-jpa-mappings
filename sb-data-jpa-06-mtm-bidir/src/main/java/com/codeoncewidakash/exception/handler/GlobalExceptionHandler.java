package com.codeoncewidakash.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewidakash.exception.SubscriberNotFoundExceptoin;
import com.codeoncewidakash.exception.TVChannelNotFoundException;
import com.codeoncewidakash.payload.ErrorReponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(exception = TVChannelNotFoundException.class)
	public ResponseEntity<ErrorReponse> handleTvChannelNotFoundException(TVChannelNotFoundException tcnfe){
		return new ResponseEntity<>(new ErrorReponse(tcnfe.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception = SubscriberNotFoundExceptoin.class)
	public ResponseEntity<ErrorReponse> handleSubscriberNotFoundException(SubscriberNotFoundExceptoin snfe){
		return new ResponseEntity<>(new ErrorReponse(snfe.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}
}
