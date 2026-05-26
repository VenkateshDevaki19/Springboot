package com.AirlineReservation.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
	
	@NotNull(message = "Flight Id is required")
	private Long flightId;
	
	@NotNull(message = "Seats count is required")
	@Min(value = 1, message = "At least 1 seat must be booked")
	private Integer seats;

}
