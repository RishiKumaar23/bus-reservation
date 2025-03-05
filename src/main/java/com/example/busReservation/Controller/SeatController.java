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
            seatService.createAndUpdateSeats(seatDto);
            String message = (seatDto.getId() != null) ? "bus seat details updated" : "Bus Seat details created";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
