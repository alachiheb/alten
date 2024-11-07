package com.alten.management.product.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler
		extends ResponseEntityExceptionHandler {
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleProductRuntimeException(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "internal server error";
		return handleExceptionInternal(ex, bodyOfResponse,
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<ExceptionInfo> handleProductNotFoundException(Exception exp, WebRequest request) {
		ExceptionInfo info = new ExceptionInfo(request.getDescription(false), exp.getLocalizedMessage());

		return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
	}



}
