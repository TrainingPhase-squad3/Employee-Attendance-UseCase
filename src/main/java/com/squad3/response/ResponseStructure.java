package com.squad3.response;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class ResponseStructure {
	private String message;
	private HttpStatus status;
	public ResponseStructure(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}
	public ResponseStructure() {
		super();
	}
}
