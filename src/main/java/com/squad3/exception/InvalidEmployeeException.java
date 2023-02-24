package com.squad3.exception;

public class InvalidEmployeeException extends RuntimeException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidEmployeeException(String message) {
		super(message);
	}

	public InvalidEmployeeException() {
		super();
	}
}
