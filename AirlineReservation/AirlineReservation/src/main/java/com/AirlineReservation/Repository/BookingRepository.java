package com.AirlineReservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AirlineReservation.Entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	
}
