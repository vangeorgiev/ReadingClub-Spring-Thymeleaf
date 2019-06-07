package com.example.demo.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.EntityAlreadyExistException;
import com.example.demo.exceptions.EntityDoesntExistsException;
import com.example.demo.exceptions.InvalidRequestException;
import com.example.demo.model.ErrorMessage;


@RestController
@ControllerAdvice
public class ExceptionController {
	
	private static final Logger log =  LoggerFactory.getLogger(ExceptionController.class);
	private static final String loggerMessage = "\nSTATUS: %s, MSG: %s, TiME %s";
	@ExceptionHandler
	public final ResponseEntity<ErrorMessage> handleInvalidRequestException(InvalidRequestException exs){
		ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST.value()
				, exs.getMessage(),LocalDateTime.now());
		logMessage(err);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler
	public final ResponseEntity<ErrorMessage> handleEntityAlreadyExistException(EntityAlreadyExistException exs){
		ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST.value()
				, exs.getMessage(),LocalDateTime.now());
		logMessage(err);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler
	public final ResponseEntity<ErrorMessage> handleEntityDoesntExistsException(EntityDoesntExistsException exs){
		ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST.value()
				, exs.getMessage(),LocalDateTime.now());
		logMessage(err);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	private void logMessage(ErrorMessage err) {
		log.error(String.format(loggerMessage, err.getStatus(), err.getMessage(), err.getExceptionTime() ));
	}
	
	

}
