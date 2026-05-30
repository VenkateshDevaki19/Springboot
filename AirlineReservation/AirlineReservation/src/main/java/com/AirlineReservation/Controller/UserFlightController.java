package com.AirlineReservation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AirlineReservation.Entity.Flight;
import com.AirlineReservation.ServiceImplementation.FlightServiceImplementation;

@RestController
@RequestMapping("/api/users/flights")
public class UserFlightController {
	
	@Autowired
	private FlightServiceImplementation flightServiceImplementation;

	//Search Flights -working
	@GetMapping("/search")
	public List<Flight> searchFlight(@RequestParam String origin, @RequestParam String destination){
		
		return flightServiceImplementation.searchFlight(origin, destination);
	}
	

}
