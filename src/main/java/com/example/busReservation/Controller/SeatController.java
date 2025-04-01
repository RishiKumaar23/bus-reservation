package com.example.busReservation.Controller;

import com.example.busReservation.Dto.SeatDto;
import com.example.busReservation.Service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seat")
public class SeatController {
    private final SeatService seatService;

    @PutMapping("/save")
    private ResponseEntity<?> createAndUpdateSeats(@RequestBody SeatDto seatDto) {
        try {
            seatService.createSeats(seatDto);

            return ResponseEntity.ok("seat categories created successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update-category")
    public ResponseEntity<?> updateSeatCategories(@RequestBody SeatDto seatDto) {
        try {
            seatService.updateSeatCategories(seatDto);
            return ResponseEntity.ok("Seat categories updated successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
