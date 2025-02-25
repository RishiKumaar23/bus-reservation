package com.example.busReservation.Controller;

import com.example.busReservation.Dto.BusDetailsDto;
import com.example.busReservation.Service.BusDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/BusDetails")
public class BusDetailsController {
    private final BusDetailsService busDetailsService;

    @PutMapping("/save")
    private ResponseEntity<?> saveBusDetails(@RequestBody BusDetailsDto busDetailsDto) {
        try {
            busDetailsService.saveBusDetails(busDetailsDto);
            String message = (busDetailsDto.getId() != null) ? "bus details updated" : "Bus details created";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    private ResponseEntity<?> getAllBusDetails() {
        try {
            return new ResponseEntity<>(busDetailsService.getAllBusDetails(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/Bus/{id}")
    private ResponseEntity<?> getBusById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(busDetailsService.getBusById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            busDetailsService.deleteById(id);
            return new ResponseEntity<>("bus details deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
