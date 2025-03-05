package com.example.busReservation.Dto;

import com.example.busReservation.Entity.Booking;
import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Entity.BusFare;
import com.example.busReservation.Enum.SeatCategory;
import com.example.busReservation.Enum.SeatType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class SeatDto {
    private Integer id;
    private BusDetails busDetails;
    private Set<String> windowSeats;
    private Set<String> otherSeats;
    private Integer seatCapacity;
    private BigDecimal fare;
    private Booking booking;
    private boolean isBooked = false;
}
