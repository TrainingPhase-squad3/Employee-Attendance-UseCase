package com.squad3.exception;

public class EmployeeNotFoundException extends RuntimeException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeeNotFoundException(String message) {
		super(message);
	}

	public EmployeeNotFoundException() {
		super();
	}
}
