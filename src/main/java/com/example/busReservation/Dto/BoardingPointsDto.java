package com.example.busReservation.Dto;

import com.example.busReservation.Entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardingPointsDto {
    private Integer id;
    private String boardingName;
    private Integer cityId;


}
