package com.example.busReservation.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusOwnerDto {
    private Integer id;
    private String OwnerName;
    private String companyName;
    private Integer noOfBuses;
    private String emailId;
    private String contactNo;
}
