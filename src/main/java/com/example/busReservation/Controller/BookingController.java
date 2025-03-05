package com.example.busReservation.Controller;

import com.example.busReservation.Dto.BookingDto;
import com.example.busReservation.Dto.BusSearchDto;
import com.example.busReservation.Entity.Booking;
import com.example.busReservation.Entity.BusSchedules;
import com.example.busReservation.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
   private final BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingDto bookingDto) {
        try {
            Booking booking = bookingService.createBooking(bookingDto);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
