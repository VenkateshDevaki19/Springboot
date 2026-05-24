package com.AirlineReservation.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "flights")
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String flightNumber;
	
	private String origin;
	
	private String destination;
	
	private LocalDateTime departureTime;
	
	private LocalDateTime arrivalTime;
	
	//total seats
	private Integer totalSeats;
	
	// remaining seats
	private Integer availableSeats;
	
	//ticket price
	private Double price;

}
