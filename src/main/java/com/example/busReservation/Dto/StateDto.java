package com.example.busReservation.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StateDto {
    private Integer id;
    private String stateName;
    private Integer countryId;
    private List<CityDto> city;
}
