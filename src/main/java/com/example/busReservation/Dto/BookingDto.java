package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusSchedules;
import com.example.busReservation.Entity.Seat;
import com.example.busReservation.Entity.UserDetails;
import com.example.busReservation.Enum.BookingStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;

import java.time.LocalDate;

@Data
@Builder
public class BookingDto {
    private Integer id;
    private UserDetails user;
    private BusSchedules busSchedule;
    private Seat seat;
    private LocalDate bookingDate;
    private BookingStatus status = BookingStatus.BOOKED;

}
