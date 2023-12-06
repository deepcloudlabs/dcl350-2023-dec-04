package com.example.hr.controller.handler;

import java.util.List;

import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.dto.error.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestControllerErrorHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public List<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
		return e.getConstraintViolations()
				.stream()
				.map( cv -> new ErrorResponse(cv.getMessage(),cv.getConstraintDescriptor().getAnnotation().toString()))
				.toList();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public List<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return e.getBindingResult()
				.getAllErrors()
				.stream()
				.map( err -> new ErrorResponse(err.getDefaultMessage(),err.getObjectName()))
				.toList();
	}
	

	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException e) {
		return new ErrorResponse(e.getMessage(), e.getClass().getName());
	}
	
	@ExceptionHandler(ExistingEmployeeException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorResponse handleExistingEmployeeException(ExistingEmployeeException e) {
		return new ErrorResponse(e.getMessage(), e.getClass().getName());
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	public ErrorResponse handleRuntimeException(RuntimeException e) {
		return new ErrorResponse(e.getMessage(), e.getClass().getName());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorResponse handleIllegalArgument(IllegalArgumentException e) {
		return new ErrorResponse(e.getMessage(), "");
	}
	
	@ExceptionHandler(MappingException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorResponse handleMappingException(MappingException e) {
		e.printStackTrace(System.err);
		return new ErrorResponse(e.getMessage(), "");
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	public ErrorResponse handleException(Exception e) {
		return new ErrorResponse(e.getMessage(), "");
	}
}
