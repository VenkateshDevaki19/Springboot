package com.AirlineReservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AirlineReservation.DTO.BookingRequest;
import com.AirlineReservation.Entity.Booking;
import com.AirlineReservation.ServiceImplementation.BookingServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user/booking")
public class BookingController {
	
	@Autowired
	private BookingServiceImpl bookingServiceImpl;

	@PostMapping("/book")
	public Booking bookTicket(@Valid @RequestBody BookingRequest bookingRequest, Authentication authentication) {
		
		String email = authentication.getName();
		return bookingServiceImpl.bookTickets(email, bookingRequest);
		
	}

}
