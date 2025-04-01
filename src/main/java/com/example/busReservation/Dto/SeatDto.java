package com.example.busReservation.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SeatDto {
    private Integer busId;
    private List<SeatEntry> seats;
}
