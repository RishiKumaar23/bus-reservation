package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Entity.City;
import com.example.busReservation.Enum.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
@Data
@Builder
public class RouteDTo {
    private Integer id;
    private BusDetails busDetails;
    private City sourceCity;
    private City destinationCity;
    private Integer distanceKm;
    private String status ;
    private Double travelTime;
}
