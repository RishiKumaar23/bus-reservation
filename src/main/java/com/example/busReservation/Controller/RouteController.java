package com.example.busReservation.Controller;

import com.example.busReservation.Dto.RouteDTo;
import com.example.busReservation.Service.RouteService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/api/route")
public class RouteController {
    private final RouteService routeService;

    @PutMapping("/save")
    private ResponseEntity<?> saveAndUpdateRoutes(@RequestBody RouteDTo routeDTo){
        try{
            routeService.saveAndUpdateRoutes(routeDTo);
            String message= (routeDTo.getId()!=null)? "Bus routes Details updated": "Bus routes Details created ";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    private ResponseEntity<?> getAllRoutes() {
        try {
            return new ResponseEntity<>(routeService.getAllRoutes(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/route/{id}")
    private ResponseEntity<?> getRouteById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(routeService.getRouteById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            routeService.deleteById(id);
            return new ResponseEntity<>("route  deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

}
