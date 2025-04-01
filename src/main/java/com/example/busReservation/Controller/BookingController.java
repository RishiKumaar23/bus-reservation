package com.example.busReservation.Controller;

import com.example.busReservation.Dto.BookingRequestDto;
import com.example.busReservation.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> bookSeat(@RequestBody BookingRequestDto bookingRequest) {
        try {
            String response = bookingService.bookSeat(bookingRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<?> cancelSeat(@PathVariable Integer bookingId) {
        try {
            String response = bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}


