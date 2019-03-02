/**
 * 
 */
package com.flowers.microservice.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@ControllerAdvice
public class ErrorHandler {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void processValidationError(IllegalArgumentException e) {
		log.info("Returning HTTP 400 Bad Request", e);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void processMethodArgumentNotValidExceptionError(IllegalArgumentException e) {
		log.info("Returning HTTP 400 Bad Request", e);
	}
	
}