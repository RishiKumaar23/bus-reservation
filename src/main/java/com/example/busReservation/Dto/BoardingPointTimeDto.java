package com.example.busReservation.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardingPointTimeDto {
    private Integer boardingPointId;
    private Integer timeInterval;
}