package com.squad3.exception;

public class EmployeeAlreadyExists extends RuntimeException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeeAlreadyExists(String message) {
		super(message);
	}

	public EmployeeAlreadyExists() {
		super();
	}
}
