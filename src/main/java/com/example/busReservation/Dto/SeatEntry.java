package com.example.busReservation.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatEntry {
    private Integer id;
    private String seatName;
    private String seatCategory;
}
