package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Entity.City;
import com.example.busReservation.Entity.RouteVariant;
import com.example.busReservation.Enum.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class RouteDTo {
    private Integer id;
    private Integer sourceCity;
    private Integer destinationCity;
    private Integer distanceKm;
    private String status ;
}
