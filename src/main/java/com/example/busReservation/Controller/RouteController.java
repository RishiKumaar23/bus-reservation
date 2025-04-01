package com.example.busReservation.Controller;

import com.example.busReservation.Dto.RouteDTo;
import com.example.busReservation.Dto.RouteVariantDto2;
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
    private ResponseEntity<?> saveAndUpdateRoutes(@RequestBody RouteDTo routeDTo) {
        try {
            routeService.saveAndUpdateRoutes(routeDTo);
            String message = (routeDTo.getId() != null) ? "Bus routes Details updated" : "Bus routes Details created ";
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

    @GetMapping("/get/{id}")
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

    @PutMapping("variant/save")
    private ResponseEntity<?> createAndUpdateRouteVariant(@RequestBody RouteVariantDto2 variantDto) {
        try {
            routeService.createAndUpdateRouteVariant(variantDto);
            String message = variantDto.getId() != null ? "Route variant updated" : "Route Variant Created";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping(value = "pickUp/dropping/Points/save",consumes = "application/json")
//    private ResponseEntity<?> createAndUpdatePickANdDroppingPoints(@RequestBody RouteVariantDto routeVariantDto) {
//        try {
//            routeService.createAndUpdateBusRoutes(routeVariantDto);
//            return ResponseEntity.ok("Pickup Points Saved Successfully");
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

}













































































//public void createAndUpdateBusRoutes(RouteVariantDto routeVariantDto) {
//    RouteVariant routeVariant = routeVariantRepository.findById(routeVariantDto.getRouteVariantId())
//            .orElseThrow(() -> new RuntimeException("RouteVariant not found"));
//
//    // Fetch all city IDs in a single query
//    Set<Long> cityIds = routeVariantDto.getBusRoutes().stream()
//            .map(BusRouteDto::getCityId)
//            .collect(Collectors.toSet());
//    Map<Long, City> cityMap = cityRepository.findAllById(cityIds).stream()
//            .collect(Collectors.toMap(City::getId, city -> city));
//
//    // Create BusRoutes in-memory
//    List<BusRoutes> busRoutes = routeVariantDto.getBusRoutes().stream()
//            .map(busRouteDto -> {
//                City city = cityMap.get(busRouteDto.getCityId());
//                if (city == null) throw new RuntimeException("City not found for ID: " + busRouteDto.getCityId());
//
//                return BusRoutes.builder()
//                        .routeVariant(routeVariant)
//                        .city(city)
//                        .stopOrder(busRouteDto.getStopOrder())
//                        .fare(busRouteDto.getFare())
//                        .build();
//            })
//            .sorted(Comparator.comparing(BusRoutes::getStopOrder))
//            .toList();
//
//    busRouteRepository.saveAll(busRoutes); // Save all at once
//
//    // Fetch all boarding points in a single query
//    Set<Long> boardingPointIds = routeVariantDto.getBusRoutes().stream()
//            .flatMap(busRouteDto -> busRouteDto.getBoardingPoints().stream())
//            .map(BoardingPointDto::getBoardingPointId)
//            .collect(Collectors.toSet());
//    Map<Long, BoardingPoints> boardingPointsMap = boardingPointsRepository.findAllById(boardingPointIds).stream()
//            .collect(Collectors.toMap(BoardingPoints::getId, bp -> bp));
//
//    LocalTime boardingTime = routeVariantDto.getStartBoardingTime();
//
//    List<BoardingPointTime> boardingPointTimes = new ArrayList<>();
//
//    for (BusRoutes busRoute : busRoutes) {
//        routeVariantDto.getBusRoutes().stream()
//                .filter(r -> r.getCityId().equals(busRoute.getCity().getId()))
//                .findFirst()
//                .ifPresent(busRouteDto -> {
//                    for (BoardingPointDto boardingPointDto : busRouteDto.getBoardingPoints()) {
//                        BoardingPoints boardingPoint = boardingPointsMap.get(boardingPointDto.getBoardingPointId());
//                        if (boardingPoint == null)
//                            throw new RuntimeException("Boarding Point not found for ID: " + boardingPointDto.getBoardingPointId());
//
//                        boardingPointTimes.add(BoardingPointTime.builder()
//                                .busRoute(busRoute)
//                                .boardingPoint(boardingPoint)
//                                .boardingTime(boardingTime)
//                                .build());
//
//                        boardingTime = boardingTime.plusMinutes(
//                                boardingPointDto.getTimeInterval() != null ? boardingPointDto.getTimeInterval() : 0
//                        );
//                    }
//                });
//    }
//
//    boardingPointTimeRepository.saveAll(boardingPointTimes); // Save all at once
//}
//public void createAndUpdateBusRoutes(RouteVariantDto routeVariantDto) {
//    RouteVariant routeVariant = routeVariantRepository.findById(routeVariantDto.getRouteVariantId())
//            .orElseThrow(() -> new RuntimeException("Route variant not found"));
//
//    // Create bus routes from the DTO
//    List<BusRoutes> busRoutes = routeVariantDto.getBusRoutes().stream()
//            .map(busRouteDto -> {
//                City city = cityRepository.findById(busRouteDto.getCityId())
//                        .orElseThrow(() -> new RuntimeException("City not found"));
//
//                return BusRoutes.builder()
//                        .routeVariant(routeVariant)
//                        .city(city)
//                        .stopOrder(busRouteDto.getStopOrder())
//                        .fare(busRouteDto.getFare())
//                        .build();
//            })
//            .sorted(Comparator.comparing(BusRoutes::getStopOrder))
//            .toList();
//
//    busRouteRepository.saveAll(busRoutes);
//
//    LocalTime initialBoardingTime = routeVariantDto.getStartBoardingTime() != null
//            ? LocalTime.parse(routeVariantDto.getStartBoardingTime())
//            : null;
//
//    // Fetch all boarding points in one go
//    List<Integer> boardingPointIds = routeVariantDto.getBusRoutes().stream()
//            .flatMap(busRouteDto -> busRouteDto.getBoardingPoints().stream())
//            .map(BoardingPointTimeDto::getBoardingPointId)
//            .collect(Collectors.toList());
//
//    Map<Integer, BoardingPoints> boardingPointMap = boardingPointsRepository.findAllById(boardingPointIds).stream()
//            .collect(Collectors.toMap(BoardingPoints::getId, Function.identity()));
//
//    for (BusRoutes busRoute : busRoutes) {
//        List<BoardingPointTime> boardingPointTimes = new ArrayList<>();
//        LocalTime boardingTime = initialBoardingTime;
//
//        // Find the corresponding BusRouteDto for the current busRoute
//        BusRouteDto currentBusRouteDto = routeVariantDto.getBusRoutes().stream()
//                .filter(r -> r.getCityId().equals(busRoute.getCity().getId()))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("No boarding points found for city ID: " + busRoute.getCity().getId()));
//
//        // Now you can access the boarding points from the currentBusRouteDto
//        List<BoardingPointTimeDto> boardingPointsDtos = currentBusRouteDto.getBoardingPoints();
//
//        for (BoardingPointTimeDto boardingPointTimeDto : boardingPointsDtos) {
//            BoardingPoints boardingPoint = boardingPointMap.get(boardingPointTimeDto.getBoardingPointId());
//            if (boardingPoint == null) {
//                throw new RuntimeException("Boarding Point not found for ID: " + boardingPointTimeDto.getBoardingPointId());
//            }
//
//            BoardingPointTime boardingPointTime = BoardingPointTime.builder()
//                    .busRoute(busRoute)
//                    .boardingPoint(boardingPoint)
//                    .boardingTime(boardingTime)
//                    .build();
//            boardingPointTimes.add(boardingPointTime);
//
//            // Increment the boarding time for the next boarding point
//            boardingTime = incrementBoardingTime(boardingTime, boardingPointTimeDto.getTimeInterval());
//        }
//
//        // Save all boarding point times in one batch operation
//        boardingPointTimeRepository.saveAll(boardingPointTimes);
//    }
//}