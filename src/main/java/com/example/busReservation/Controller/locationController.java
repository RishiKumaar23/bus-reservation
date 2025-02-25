package com.example.busReservation.Controller;

import com.example.busReservation.Dto.CountryDto;
import com.example.busReservation.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/location")
public class locationController {
    private final LocationService locationService;

    @PutMapping("/createAndUpdateLocation")
    private ResponseEntity<?> createAndUpdateLocation(@RequestBody CountryDto countryDto) {
        try {
            locationService.createAndUpdateLocation(countryDto);
            String message = (countryDto.getId() != null) ? "Location updated successfully" : "Location added successfully";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteCountry(@PathVariable Integer country_id) {
        try {
            locationService.deleteCountry(country_id);
            return new ResponseEntity<>("country deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/stateDelete")
    private ResponseEntity<?> deleteState(@PathVariable Integer state_id) {
        try {
            locationService.deleteState(state_id);
            return new ResponseEntity<>("state deleted successfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/cityDelete")
    private ResponseEntity<?> deleteCity(@PathVariable Integer city_id) {
        try {
            locationService.deleteCity(city_id);
            return new ResponseEntity<>("state deleted successfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}




