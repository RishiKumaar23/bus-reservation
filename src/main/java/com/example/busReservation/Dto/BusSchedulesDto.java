package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Entity.Route;
import com.example.busReservation.Entity.TravelDates;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class BusSchedulesDto {
    private Integer id;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Double travelTime;
    private Set<LocalDate> travelDates;
    private BusDetails busDetails;
    private Route route;
}
