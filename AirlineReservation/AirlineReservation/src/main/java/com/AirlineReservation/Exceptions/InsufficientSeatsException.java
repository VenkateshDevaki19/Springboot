package com.AirlineReservation.Exceptions;

public class InsufficientSeatsException extends RuntimeException{

	// Create insufficient seats exception
    public InsufficientSeatsException(String message) {
        super(message);
    }
}
