package com.example.busReservation.Dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PickUpPointsDto {
    private Integer routeVariantId;
    private List<PickUpPointRequestDto> pickUpPoints;


}
