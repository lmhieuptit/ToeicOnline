package com.fsoft.ez.common.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.fsoft.ez.common.exception.NotFoundException;
import com.fsoft.ez.common.model.RestErrorInfo;

@RestControllerAdvice
public class EZResponseExceptionHandler {

	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected Map<String, Object> handleBindingDataNotValid(BindException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		// Get all errors
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		body.put("message", errors);
		return body;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected Map<String, Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		// Get all errors
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		body.put("message", errors);
		return body;
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<RestErrorInfo> handleAllExceptions(Exception ex, WebRequest request) {
		ex.printStackTrace();
		if (ex instanceof ResponseStatusException) {
			ResponseStatusException exception = (ResponseStatusException) ex;
			RestErrorInfo errorDetails = new RestErrorInfo(new Date(), exception.getReason(),
					request.getDescription(false));
			return new ResponseEntity<>(errorDetails, exception.getStatus());
		}
		RestErrorInfo errorDetails = new RestErrorInfo(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<RestErrorInfo> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
		RestErrorInfo errorDetails = new RestErrorInfo(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
