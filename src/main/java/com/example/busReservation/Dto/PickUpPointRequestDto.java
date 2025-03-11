package com.example.busReservation.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public  class PickUpPointRequestDto {
        private Integer cityId;
        private Integer stopOrder;
        private Double fare;

}
