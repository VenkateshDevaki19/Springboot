package com.AirlineReservation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.AirlineReservation.Entity.Flight;
import com.AirlineReservation.ServiceImplementation.FlightServiceImplementation;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
	
	@Autowired
	private FlightServiceImplementation serviceImplementation;
	
	@PostMapping("/AddFlight")
	private Flight addFlight(@RequestBody Flight flight) {
		logger.debug("method has reached controller class - 2");
		return serviceImplementation.addFlight(flight);
	}
	
	@GetMapping("/AllFlights")
	private List<Flight> getAllFlights(){
		return serviceImplementation.getAllFlights();
	}
	
	@GetMapping("/search")
	private List<Flight> searchFlights(@RequestParam String origin, @RequestParam String destination){
		logger.debug("method has reached to search flight 1", origin);
		logger.debug("method has reached to search flight 2", destination);
		return serviceImplementation.searchFlight(origin, destination);
		
	}

}
