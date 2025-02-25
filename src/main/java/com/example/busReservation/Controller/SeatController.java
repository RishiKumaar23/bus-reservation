package com.example.busReservation.Controller;

import com.example.busReservation.Dto.SeatDto;
import com.example.busReservation.Service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seat")
public class SeatController {
    private final SeatService seatService;

@PutMapping("/save")
    private ResponseEntity<?> saveAndUpdateSeats(@RequestBody SeatDto seatDto){
        try{
            seatService.saveAndUpdateSeats(seatDto);
            String message=(seatDto.getId()!=null)?"seat availability updated": "seat availability created";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
