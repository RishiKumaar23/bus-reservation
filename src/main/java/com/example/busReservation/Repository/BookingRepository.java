package com.example.busReservation.Repository;

import com.example.busReservation.Entity.Booking;
import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Entity.BusTravelDate;
import com.example.busReservation.Entity.Seat;
import com.example.busReservation.Enum.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "select count(*) from bookings b " +
            "where b.seat_id = :seatId " +
            "and b.bus_id = :busId " +
            "and b.travel_date_id = :travelDateId and b.status =:status", nativeQuery = true)
    Long countBookingsBySeatAndBusAndDate(@Param("seatId") Integer seatId,
                                          @Param("busId") Integer busId,
                                          @Param("travelDateId") Integer travelDateId,
                                          @Param("status") BookingStatus status);
}
