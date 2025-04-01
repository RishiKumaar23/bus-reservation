package com.example.busReservation.Repository;

import com.example.busReservation.Dto.SeatBookingDto;
import com.example.busReservation.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,Integer> {

}


