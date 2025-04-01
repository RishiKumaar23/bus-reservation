package com.example.busReservation.Dto;

import lombok.Data;
import lombok.Getter;
@Data
@Getter
public class SeatBookingDto {
        private Integer id;
        private String seatName;
        private Boolean isBooked;
}
