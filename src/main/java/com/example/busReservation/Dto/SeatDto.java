package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatDto {
    private Integer id;
    private BusDetails bus;
    private Integer noOfWindowSeats;
    private Integer noOfOtherSeats;
    private Integer seatCapacity;


}
