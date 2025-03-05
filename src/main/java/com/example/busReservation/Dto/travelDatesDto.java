package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusDetails;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class travelDatesDto {
    private Integer id;
    private LocalDate travelDate;
    private BusDetails busDetails;
}
