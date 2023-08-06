package com.shop.productservice.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shop.productservice.dto.error.ErrorDetails;
import com.shop.productservice.dto.error.ValidationError;
import com.shop.productservice.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		ErrorDetails details = new ErrorDetails();
		details.setTimestamp(new Date().getTime());
		details.setTitle("Message Not Readable");
		details.setStatus(status.value());
		details.setDeveloperMessage(ex.getClass().getName());
		details.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, details, headers, status, request);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe)
	{
		ErrorDetails details = new ErrorDetails();
		details.setTitle("Resource Not Found");
		details.setDeveloperMessage(rnfe.getClass().getName());
		details.setMessage(rnfe.getMessage());
		details.setStatus(HttpStatus.NOT_FOUND.value());
		details.setTimestamp(new Date().getTime());
		
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		ErrorDetails details = new ErrorDetails();

		details.setTitle("Field validation error.");
		details.setDeveloperMessage(ex.getClass().getName());
		details.setMessage("Input validation error.");
		details.setTimestamp(new Date().getTime());
		details.setStatus(HttpStatus.BAD_REQUEST.value());
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();

		for (ObjectError e : errors) {
			List<ValidationError> list = details.getErrors().get(e.getObjectName());

			if (list == null)
			{
				list = new ArrayList<>();
				details.getErrors().put(e.getObjectName(), list);
			}

			ValidationError error = new ValidationError();

			error.setCode(e.getCode());
			error.setMessage(e.getDefaultMessage());

			list.add(error);
		}

		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

}
