package com.example.busReservation.Dto;

import com.example.busReservation.Enum.FilterBy;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BusSearchDto {
    private Integer sourceCityId;
    private Integer destinationCityId;
    private LocalDate travelDate;
    private String busType;
    private String seatType;
    private String customStartTime;
    private String customEndTime;
    private FilterBy FilterBy;

}

