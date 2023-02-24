package com.squad3.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.squad3.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	
	@ExceptionHandler(value = InvalidEmployeeException.class)
	public ResponseEntity<Object> invalidEmployeeException(InvalidEmployeeException ex, WebRequest req) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseStructure(ex.getMessage(), HttpStatus.BAD_REQUEST));

	}

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<Object> nullPointerException(NullPointerException ex, WebRequest req) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseStructure("Invalid employee", HttpStatus.NOT_FOUND));
}
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(EmployeeNotFoundException exception) {
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(exception.getMessage(),HttpStatus.NOT_FOUND));		

	}

}
