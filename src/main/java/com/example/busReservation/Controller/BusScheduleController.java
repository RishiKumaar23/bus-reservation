package com.example.busReservation.Controller;

import com.example.busReservation.Dto.BusSchedulesDto;
import com.example.busReservation.Dto.BusSearchDto;
import com.example.busReservation.Service.BusScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/BusSchedule")
public class BusScheduleController {
    private final BusScheduleService busScheduleService;

    @PutMapping("/save")
    private ResponseEntity<?> saveBusSchedules(@RequestBody BusSchedulesDto busSchedulesDto) {
        try {
            busScheduleService.saveBusSchedules(busSchedulesDto);
            String message = (busSchedulesDto.getId() != null) ? "Bus Schedules updated" : "Bus Schedules created ";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> availableBuses(@RequestBody BusSearchDto busSearchDto) {
        try {
            List<BusSchedulesDto> buses = busScheduleService.availableBuses(busSearchDto);
            return ResponseEntity.ok(buses);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
