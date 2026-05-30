package com.AirlineReservation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.AirlineReservation.DTO.FlightRequest;
import com.AirlineReservation.Entity.Flight;
import com.AirlineReservation.ServiceImplementation.FlightServiceImplementation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/flights")
public class FlightController {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
	
	@Autowired
	private FlightServiceImplementation serviceImplementation;
	
	@PostMapping("/AddFlight")
	private Flight addFlight(@Valid @RequestBody FlightRequest request) {
		logger.debug("Flight adding here");
		log.info("Flight successfully added!!");
		return serviceImplementation.addFlight(request);
	}
	
	@PostMapping("/AddFlight1")
	private Flight addFlight(@Valid @RequestBody Flight flight) {
		logger.debug("AddFlight1 adding here");
		log.info("AddFlight1 successfully added!!");
		return serviceImplementation.addFlight1(flight);
	}
	
	
	@GetMapping("/AllFlights")
	private Page<Flight> getAllFlights(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
		return serviceImplementation.getAllFlights(page, size);
	}
	
	//working
	@GetMapping("/search")
	private List<Flight> searchFlights(@RequestParam String origin, @RequestParam String destination){
		logger.debug("method has reached to search flight 1", origin);
		logger.debug("method has reached to search flight 2", destination);
		return serviceImplementation.searchFlight(origin, destination);
		
	}
	
	@PutMapping("/{id}")
	public Flight updateFlight(@PathVariable String id, @Valid @RequestBody FlightRequest flight) {
		return serviceImplementation.updateFlight(id, flight);
	}
	
	@DeleteMapping("/delete/{flightNumber}")
	public String deleteFlight(@PathVariable String flightNumber) {
		log.info("Controller {} : ", flightNumber);
		serviceImplementation.deleteFlight(flightNumber);
		return "Flight deleted successfully!";
	}

}
