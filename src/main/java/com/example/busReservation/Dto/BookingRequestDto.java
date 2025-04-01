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
public class BookingRequestDto {
    private Integer userId;
    private Integer seatId;
    private Integer busId;
    private Integer travelDateId;
    private Integer boardingPointId;
    private BookingStatus status;

}
