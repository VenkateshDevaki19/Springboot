package com.AirlineReservation.Service;

import java.util.List;

import com.AirlineReservation.Entity.Flight;

public interface FlightServiceInt {
	
	//add new flight
	Flight addFlight(Flight flight);
	
	//get all flights
	List<Flight> getAllFlights();
	
	//search flights
	List<Flight> searchFlight(String Origin, String Destination);

}
