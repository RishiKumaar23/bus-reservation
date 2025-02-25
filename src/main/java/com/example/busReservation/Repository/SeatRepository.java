package com.example.busReservation.Repository;

import com.example.busReservation.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
  @Query(value = "select * from seat_availability where id=:id",nativeQuery = true)
    Optional<Seat> findBySeatId (@Param("id")Integer seatId);
}
