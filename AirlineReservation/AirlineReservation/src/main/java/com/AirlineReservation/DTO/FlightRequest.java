package com.AirlineReservation.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequest {

	@NotBlank(message = "Flight number is required")
	private String flightNumber;
	
	@NotBlank(message = "Origin is required")
	private String origin;
	
	@NotBlank(message = "Destination is required")
	private String destination;
	
	@NotNull(message = "Departure time is required")
	private LocalDateTime departureTime;
	
	@NotNull(message = "Arrival time is required")
	private LocalDateTime arrivalTime;
	
	//total seats
	@NotNull(message = "Total seats are required")
	@Min(value = 1, message = "Total seats must be at least 1")
	private Integer totalSeats;
	
	// remaining seats
	@NotNull(message = "Available seats are required")
	@Min(value = 0, message = "Available seats cannoy be negative")
	private Integer availableSeats;
	
	//ticket price
	@NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
	private Double price;
}
