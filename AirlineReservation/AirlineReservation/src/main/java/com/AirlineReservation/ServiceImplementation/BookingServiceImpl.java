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
		log.info("Booking request received");
		User user = userRepository.findByEmail(email)
				.orElseThrow( () -> new ResourceNotFoundException("User not found: "+ email));
		
		log.info("User email: {}", email);
		
		Flight flight = flightRepository.findById(request.getFlightId())
				.orElseThrow( () -> new ResourceNotFoundException("Flight not found: "+ request.getFlightId()));
		
		log.info("Flight ID: {}", request.getFlightId());
		
		// seats availability 
		if(flight.getAvailableSeats() < request.getSeats()) {
			throw new InsufficientSeatsException("Not enough seats. Available: " + flight.getAvailableSeats()
            + ", Requested: " + request.getSeats());
		}
		
		flight.setAvailableSeats(flight.getAvailableSeats() - request.getSeats());
		flightRepository.save(flight);
		
		log.info("Seats requested: {}", request.getSeats());
		
		Booking booking = Booking.builder()
		        .user(user)       
		        .flight(flight)   
		        .seatsBooked(request.getSeats())
		        .bookingDate(LocalDateTime.now())
		        .build();
		
		log.info("Booking created: id={}, user={}", email);
	
		return bookingRepository.save(booking);
	}

}
