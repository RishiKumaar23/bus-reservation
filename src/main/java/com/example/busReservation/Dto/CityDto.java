package com.example.busReservation.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDto {
    private Integer id;
    private String cityName;
}
