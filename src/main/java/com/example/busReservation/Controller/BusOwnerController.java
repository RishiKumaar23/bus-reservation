package com.example.busReservation.Controller;

import com.example.busReservation.Dto.BusOwnerDto;
import com.example.busReservation.Service.BusOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ownerDetails")
public class BusOwnerController {
    private final BusOwnerService busOwnerService;

    @PutMapping("/save")
    private ResponseEntity<?> saveBusOwnerDetails(@RequestBody BusOwnerDto busOwnerDto){
        try{
            busOwnerService.saveBusOwnerDetails(busOwnerDto);
            String message= (busOwnerDto.getId()!=null)? "Bus owner Details updated": "Bus owner Details created ";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    private ResponseEntity<?> getAllOwners() {
        try {
            return new ResponseEntity<>(busOwnerService.getAllOwners(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/owner/{id}")
    private ResponseEntity<?> getOwnerById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(busOwnerService.getOwnerById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            busOwnerService.deleteOwnerById(id);
            return new ResponseEntity<>("busOwner details deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

}
