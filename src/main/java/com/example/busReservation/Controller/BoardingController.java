package com.example.busReservation.Controller;

import com.example.busReservation.Dto.BoardingPointDto;
import com.example.busReservation.Service.BoardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boardingPoint")
public class BoardingController {
    private final BoardingService boardingService;

    @PutMapping("/save")
    public ResponseEntity<String> createAndUpdateBoarding(@RequestBody List<BoardingPointDto> boardingDtos) {
        try {
            for (BoardingPointDto boardingDto : boardingDtos) {
                boardingService.createAndUpdateBoarding(boardingDto);
            }
            return ResponseEntity.ok("Boarding points processed successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBoardingPoints(@PathVariable Integer id){
        try{
            boardingService.deleteBoardingPoint(id);
            return new ResponseEntity<>("boarding point is deleted",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}