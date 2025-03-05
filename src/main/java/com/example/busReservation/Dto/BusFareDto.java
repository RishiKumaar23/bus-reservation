package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Entity.Route;
import com.example.busReservation.Entity.Seat;
import com.example.busReservation.Enum.SeatCategory;
import com.example.busReservation.Enum.SeatType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class BusFareDto {
    private Integer id;
    private Seat seat;
    private BigDecimal fare;
    private String status;
}
