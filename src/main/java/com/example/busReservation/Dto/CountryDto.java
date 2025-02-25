package com.example.busReservation.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CountryDto {
    private Integer id;
    private String countryName;
   private List<StateDto> states;
}
