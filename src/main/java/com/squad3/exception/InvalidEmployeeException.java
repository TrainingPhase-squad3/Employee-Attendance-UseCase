package com.squad3.exception;

public class InvalidEmployeeException extends RuntimeException {



	private static final long serialVersionUID = 1L;
	String message;

	public InvalidEmployeeException(String message) {
		super(message);
		
	}
}
