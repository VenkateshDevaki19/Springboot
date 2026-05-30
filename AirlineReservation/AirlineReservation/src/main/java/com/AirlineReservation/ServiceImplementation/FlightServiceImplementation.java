package com.AirlineReservation.ServiceImplementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AirlineReservation.DTO.FlightRequest;
import com.AirlineReservation.Entity.Flight;
import com.AirlineReservation.Exceptions.ResourceNotFoundException;
import com.AirlineReservation.Repository.BookingRepository;
import com.AirlineReservation.Repository.FlightRepository;
import com.AirlineReservation.Service.FlightServiceInt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightServiceImplementation implements FlightServiceInt{
	
	private static final Logger logger = LoggerFactory.getLogger(FlightServiceImplementation.class);
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public Flight addFlight1(Flight flight) {	
		logger.debug("method has reached to service class - 1");
		return flightRepository.save(flight);
	}

//	@Override
//	public List<Flight> getAllFlights() {
//		return flightRepository.findAll();
//	}
	
	@Override
	public Page<Flight> getAllFlights(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return flightRepository.findAll(pageable);
	}

	@Override
	public List<Flight> searchFlight(String Origin, String Destination) {
		logger.debug("method has reached to search flight 3");
		return flightRepository.findByOriginAndDestination(Origin, Destination);
	}

	@Override
	public Flight addFlight(FlightRequest request) {
		log.info("Adding flight: {}", request.getFlightNumber());
		Flight flight = Flight.builder()
				.flightNumber(request.getFlightNumber())
				.origin(request.getOrigin())
				.destination(request.getDestination())
				.departureTime(request.getDepartureTime())
				.arrivalTime(request.getArrivalTime())
				.totalSeats(request.getTotalSeats())
				.availableSeats(request.getAvailableSeats())
				.price(request.getPrice())
				.build();
		log.info("Flight added : {}", flight.getFlightNumber());
		return flightRepository.save(flight);
	}

	@Override
	@Transactional
	public void deleteFlight(String flightNumber) {
//		log.info("Deleting flight {}", id);
//		Flight flight = flightRepository.findById(id)
//				.orElseThrow( () -> new ResourceNotFoundException("Flight not found"));
//		log.info("Flight Details: {}", flight.getFlightNumber());
//		bookingRepository.deleteById(id);
//		flightRepository.delete(flight);
//		log.info("Flight Deleted: {}", id);
		
		Flight flight = flightRepository.findByFlightNumber(flightNumber)
	            .orElseThrow(() -> new ResourceNotFoundException("Flight not found: " + flightNumber));
	    bookingRepository.deleteByFlightId(flight.getId());
	    flightRepository.delete(flight);
	}

	@Override
	public Flight updateFlight(String flightNumber, FlightRequest request) {
		    Flight flight = flightRepository.findByFlightNumber(flightNumber)
		            .orElseThrow(() -> new ResourceNotFoundException("Flight not found: " + flightNumber));

		    flight.setFlightNumber(request.getFlightNumber());
		    flight.setOrigin(request.getOrigin());
		    flight.setDestination(request.getDestination());
		    flight.setDepartureTime(request.getDepartureTime());
		    flight.setArrivalTime(request.getArrivalTime());
		    flight.setTotalSeats(request.getTotalSeats());
		    flight.setAvailableSeats(request.getAvailableSeats());
		    flight.setPrice(request.getPrice());

		    log.info("Flight Updated: {}", flightNumber);
		    return flightRepository.save(flight);
	}

}
