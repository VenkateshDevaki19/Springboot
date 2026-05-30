package com.AirlineReservation.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.AirlineReservation.DTO.FlightRequest;
import com.AirlineReservation.Entity.Flight;

public interface FlightServiceInt {
	
	//add new flight -updated Flight class to FlightRequest DTO
	Flight addFlight(FlightRequest flight);
	
	Flight addFlight1(Flight flight);
	
	//update flight
	//Flight updateFlight(String id, FlightRequest request);
	
	Flight updateFlight(String flightNumber, FlightRequest request);
	
	//delete flight
	void deleteFlight(String flightNumber);
	
	//get all flights
	Page<Flight> getAllFlights(int page, int size);
	
	//search flights
	List<Flight> searchFlight(String Origin, String Destination);

}
