package com.AirlineReservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AirlineReservation.Entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	

@Modifying
@Query("DELETE FROM Booking b WHERE b.flight.id = :flightId")
void deleteByFlightId(@Param("flightId") Long flightId);

}
