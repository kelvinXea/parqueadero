package com.ceiba.adn.parqueadero.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ceiba.adn.parqueadero.domain.exception.ParqueaderoException;

@ControllerAdvice
public class ParqueaderoExceptionHandlerAdvice {
	
	@ExceptionHandler(value = ParqueaderoException.class)
	protected ResponseEntity<Object> handleException (ParqueaderoException e) {
		  return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body('"'+e.getMessage()+'"');
	}

}
