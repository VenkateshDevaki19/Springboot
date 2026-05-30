package com.AirlineReservation.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.AirlineReservation.DTO.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// Handle resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException ex) {
    	log.warn("Not found: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Handle duplicate resource
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse> handleDuplicateResource(DuplicateResourceException ex) {
    	 log.warn("Duplicate: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }

    // Handle seat issue
    @ExceptionHandler(InsufficientSeatsException.class)
    public ResponseEntity<ApiResponse> handleInsufficientSeats(InsufficientSeatsException ex) {
    	log.warn("Insufficient seats: {}", ex.getMessage());
    	return new ResponseEntity<>(new ApiResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        
    	Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        log.warn("Validation failed: {}", errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle generic errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
    	log.error("Unexpected error: ", ex);
        return new ResponseEntity<>(new ApiResponse("Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
