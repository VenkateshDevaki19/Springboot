package com.AirlineReservation.Service;

import com.AirlineReservation.DTO.BookingRequest;
import com.AirlineReservation.Entity.Booking;

public interface BookingService {
	
	Booking bookTickets(String email, BookingRequest request);

}
