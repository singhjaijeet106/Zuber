package com.example.demo.config;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse<List<String>>> handleValidationException(ValidationException exception) {
		ErrorResponse<List<String>> errorResponse = ErrorResponse.<List<String>>builder().type(exception.getErrorList())
				.message(exception.getMessage()).build();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
	}

	@ModelAttribute
	public void excludeSwaggerUrls(HttpServletRequest request) {
		if (request.getRequestURI().startsWith("/swagger-ui.html")
				|| request.getRequestURI().startsWith("/v3/api-docs")) {
			// Do nothing, allow Swagger UI requests to pass through.
		}
	}

}
