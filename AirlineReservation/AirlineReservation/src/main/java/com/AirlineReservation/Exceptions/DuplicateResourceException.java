package com.AirlineReservation.Exceptions;

public class DuplicateResourceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 // Create duplicate resource exception
    public DuplicateResourceException(String message) {
        super(message);
    }
}
