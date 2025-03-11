package com.example.busReservation.Dto;

import com.example.busReservation.Entity.PickUpPoints;
import com.example.busReservation.Entity.Route;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class RouteVariantDto {
    private Integer id;
    private Route route;
    private List<PickUpPoints> pickUpPoints;
    private String variantName;
}
