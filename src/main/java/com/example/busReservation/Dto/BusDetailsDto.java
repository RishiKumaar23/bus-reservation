package com.example.busReservation.Dto;

import com.example.busReservation.Entity.*;
import com.example.busReservation.Enum.SeatType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class BusDetailsDto {
    private Integer id;
    private String busRegNo;
    private String busType;
    private String seatType;
    private BusOwner owner;
    private List<Seat> seat;
    private List<BusSchedules> busSchedules;
    private List<TravelDates> travelDates;
}
