package com.example.demo.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private List<String> errorList;

	public ValidationException(String message,List<String> errorList) {
		this.message = message;
		this.errorList = errorList;
	}
}
