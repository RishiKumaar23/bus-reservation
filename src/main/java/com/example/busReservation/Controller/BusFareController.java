package com.example.busReservation.Controller;

import com.example.busReservation.Dto.BusDetailsDto;
import com.example.busReservation.Dto.BusFareDto;
import com.example.busReservation.Service.BusFareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/BusFare")
public class BusFareController {
    private final BusFareService busFareService;

    @PutMapping("/save")
    private ResponseEntity<?> saveBusDetails(@RequestBody BusFareDto busFareDto) {
        try {
            busFareService.saveBusFare(busFareDto);
            String message = (busFareDto.getId() != null) ? "bus fare updated" : "Bus fare created";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    private ResponseEntity<?> getAllBusFare() {
        try {
            return new ResponseEntity<>(busFareService.getAllBusFare(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/owner/{id}")
    private ResponseEntity<?> getBusFarById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(busFareService.getBusFarById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteByFare(@PathVariable Integer id) {
        try {
            busFareService.deleteByFareId(id);
            return new ResponseEntity<>("bus fare  deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
