package com.example.busReservation.Dto;

import com.example.busReservation.Entity.BusDetails;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BusOwnerDto {
    private Integer id;
    private String OwnerName;
    private String companyName;
    private Integer noOfBuses;
    private String emailId;
    private String contactNo;
    private List<BusDetails> buses;
}
