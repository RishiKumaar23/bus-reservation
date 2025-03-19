package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusRoutes;
import com.example.busReservation.Entity.Route;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class RouteVariantDto2 {
    private Integer id;
    private Route route;
    private List<BusRoutes> pickUpPoints;
    private String variantName;
}
