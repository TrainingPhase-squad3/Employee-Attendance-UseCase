package com.squad3.exception;





public class AccessDeniedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException() {
		super();
	}
}

