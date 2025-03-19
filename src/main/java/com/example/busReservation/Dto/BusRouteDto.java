package com.example.busReservation.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusRouteDto {
    private Integer cityId;
    private Integer stopOrder;
    private Double fare;
    private List<BoardingPointTimeDto> boardingPointTimeDto;
}


