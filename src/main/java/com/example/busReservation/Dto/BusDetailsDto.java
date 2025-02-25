package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusOwner;
import com.example.busReservation.Entity.Route;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class BusDetailsDto {
    private Integer id;
    private String busRegNo;
    private String busType;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Integer seatCapacity;
    private Route route;
    private BusOwner owner;
}
