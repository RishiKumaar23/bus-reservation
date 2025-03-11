package com.example.busReservation.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class BoardingPointDto {
    private Integer id;
    private String BoardingName;
    private Integer cityId;
}
