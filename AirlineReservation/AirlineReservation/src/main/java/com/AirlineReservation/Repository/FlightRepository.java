package com.AirlineReservation.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AirlineReservation.Entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{

	List<Flight> findByOriginAndDestination(String Origin, String Destination);

	void deleteByFlightNumber(String flightNumber);
	
	Optional<Flight> findByFlightNumber(String flightNumber);

}
