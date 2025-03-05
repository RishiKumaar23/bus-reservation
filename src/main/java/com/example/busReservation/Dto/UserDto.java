package com.example.busReservation.Dto;

import com.example.busReservation.Entity.Country;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private Long adharId;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String nationality;
    private Country country;
    private String status;

}
