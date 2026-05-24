package com.AirlineReservation.ServiceImplementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AirlineReservation.Entity.Flight;
import com.AirlineReservation.Repository.FlightRepository;
import com.AirlineReservation.Service.FlightServiceInt;

@Service
public class FlightServiceImplementation implements FlightServiceInt{
	
	private static final Logger logger = LoggerFactory.getLogger(FlightServiceImplementation.class);
	
	@Autowired
	private FlightRepository flightRepository;

	@Override
	public Flight addFlight(Flight flight) {	
		logger.debug("method has reached to service class - 1");
		return flightRepository.save(flight);
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	public List<Flight> searchFlight(String Origin, String Destination) {
		logger.debug("method has reached to search flight 3");
		return flightRepository.findByOriginAndDestination(Origin, Destination);
	}

}
