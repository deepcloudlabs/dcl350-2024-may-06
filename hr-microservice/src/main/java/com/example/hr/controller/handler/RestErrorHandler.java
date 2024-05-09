package com.example.hr.controller.handler;

import java.util.stream.Collectors;

import org.modelmapper.MappingException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hr.dto.error.RestErrorMessage;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestErrorHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
		System.err.println(e.getClass().getName());
		return new RestErrorMessage(e.getMessage(), 100, "5b0d4d5e-f161-11ec-8ea0-0242ac120002");
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	public RestErrorMessage handleRuntimeException(RuntimeException e) {
		System.err.println(e.getClass().getName());
		return new RestErrorMessage(e.getMessage());
	}


	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleConstraintViolationException(ConstraintViolationException e) {
		System.err.println(e.getClass().getName());
		var violations = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining("|"));
		return new RestErrorMessage(violations);
	}
	
	@ExceptionHandler(MappingException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestErrorMessage MappingException(MappingException e) {
		System.err.println(e.getClass().getName());
		var message = e.getErrorMessages().stream().map(ErrorMessage::getMessage).collect(Collectors.joining(","));
		return new RestErrorMessage(message);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.err.println(e.getClass().getName());
		var violations = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("|"));
		return new RestErrorMessage(violations);
	}
}

