package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Entity.BusTravelDate;
import com.example.busReservation.Entity.Route;
import com.example.busReservation.Entity.RouteVariant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusSchedulesDto {
    private Integer id;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Double travelTime;
    private Set<BusTravelDate> travelDates;
    private BusDetails busDetails;
    private Route route;
    private RouteVariant routeVariant;
    private List<BusRouteDto> busRoutesDto;
    private Integer availableSeats;
    private String SeatIds;
    private String SeatNames;
    private LocalDate travelDate;
    private String availableSeatIds;
    private String availableSeatNames;

}
