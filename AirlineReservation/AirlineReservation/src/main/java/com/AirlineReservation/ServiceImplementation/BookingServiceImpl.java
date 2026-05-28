package com.AirlineReservation.ServiceImplementation;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AirlineReservation.DTO.BookingRequest;
import com.AirlineReservation.Entity.Booking;
import com.AirlineReservation.Entity.Flight;
import com.AirlineReservation.Entity.User;
import com.AirlineReservation.Exceptions.InsufficientSeatsException;
import com.AirlineReservation.Exceptions.ResourceNotFoundException;
import com.AirlineReservation.Repository.BookingRepository;
import com.AirlineReservation.Repository.FlightRepository;
import com.AirlineReservation.Repository.UserRepository;
import com.AirlineReservation.Service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public Booking bookTickets(String email, BookingRequest request) {
		User user = userRepository.findByEmail(email)
				.orElseThrow( () -> new ResourceNotFoundException("User not found"));
		
		Flight flight = flightRepository.findById(request.getFlightId())
				.orElseThrow( () -> new ResourceNotFoundException("Flight not found"));
		
		// seats availability 
		if(flight.getAvailableSeats() < request.getSeats()) {
			throw new InsufficientSeatsException("Not enough seats available");
		}
		
		flight.setAvailableSeats(flight.getAvailableSeats() - request.getSeats());
		flightRepository.save(flight);
		
		Booking booking  = Booking.builder()
				.userId(user)
				.flightId(flight)
				.seatsBooked(request.getSeats())
				.bookingDate(LocalDateTime.now())
				.build();
		
		log.info("Booking created for user : {}, flight: {}", email, flight.getFlightNumber());
		return bookingRepository.save(booking);
	}

}
