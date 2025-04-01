package com.example.busReservation.Service;

import com.example.busReservation.Dto.RouteDTo;
import com.example.busReservation.Dto.RouteVariantDto2;
import com.example.busReservation.Entity.Route;
import com.example.busReservation.Entity.RouteVariant;
import com.example.busReservation.Enum.Status;
import com.example.busReservation.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final CityRepository cityRepository;
    private final RouteVariantRepository routeVariantRepository;
    private final BoardingRepository boardingPointsRepository;
    private final BusRouteRepository busRouteRepository;
    private final BoardingPointTimeRepository boardingPointTimeRepository;

    private RouteDTo entityToDto(Route route) {
        return RouteDTo.builder()
                .id(route.getId())
                .sourceCity(route.getSourceCity())
                .destinationCity(route.getDestinationCity())
                .distanceKm(route.getDistanceKm())
                .status(route.getStatus().toString()).build();
    }

    public void saveAndUpdateRoutes(RouteDTo routeDTo) {
        if (routeDTo.getSourceCity().equals(routeDTo.getDestinationCity())) {
            throw new RuntimeException("change the different location ");
        }
        Route route = Route.builder()
                .sourceCity(routeDTo.getSourceCity())
                .destinationCity(routeDTo.getDestinationCity())
                .distanceKm(routeDTo.getDistanceKm())
                .status(Status.valueOf(routeDTo.getStatus())).build();
        if (routeDTo.getId() != null) {
            routeRepository.findByRouteId(routeDTo.getId()).orElseThrow(() -> new RuntimeException("route is not found"));
            route.setId(routeDTo.getId());
        }
        routeRepository.save(route);
    }

    public List<RouteDTo> getAllRoutes() {
        return routeRepository.findAll().stream().map(this::entityToDto).toList();
    }

    public RouteDTo getRouteById(Integer id) {
        Optional<Route> route = routeRepository.findById(id);
        return this.entityToDto(route.get());
    }

    public void deleteById(Integer id) {
        Route route = routeRepository.findByRouteId(id).orElseThrow(() -> new RuntimeException("route id  is not found for delete"));
        routeRepository.delete(route);
    }


    public void createAndUpdateRouteVariant(RouteVariantDto2 variantDto) {
        Route route = routeRepository.findById(variantDto.getRoute().getId())
                .orElseThrow(() -> new EntityNotFoundException("Route not found"));

        RouteVariant routeVariant = RouteVariant.builder()
                .variantName(variantDto.getVariantName())
                .route(route)
                .build();

        if (variantDto.getId() != null) {
            routeVariantRepository.findById(variantDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Variant ID not found"));
            routeVariant.setId(variantDto.getId());
        }

        routeVariantRepository.save(routeVariant);
    }

//    public void createAndUpdateBusRoutes(RouteVariantDto routeVariantDto) {
//        if (routeVariantDto.getBusRoutesDto() == null || routeVariantDto.getBusRoutesDto().isEmpty()) {
//            throw new IllegalArgumentException("Bus routes data is required.");
//        }
//
//        RouteVariant routeVariant = routeVariantRepository.findById(routeVariantDto.getRouteVariantId())
//                .orElseThrow(() -> new RuntimeException("Route variant not found"));
//
//        // Collect all unique city IDs
//        Set<Integer> cityIds = routeVariantDto.getBusRoutesDto().stream()
//                .map(BusRouteDto::getCityId)
//                .collect(Collectors.toSet());
//
//        // Fetch all required cities in one query and store in a map
//        Map<Integer, City> cityMap = cityRepository.findAllById(cityIds).stream()
//                .collect(Collectors.toMap(City::getId, city -> city));
//
//        // Build BusRoutes efficiently using a sorted structure (TreeMap for auto-sorting)
//        TreeMap<Integer, BusRoutes> busRoutesMap = new TreeMap<>();
//
//        for (BusRouteDto busRouteDto : routeVariantDto.getBusRoutesDto()) {
//            City city = cityMap.get(busRouteDto.getCityId());
//            if (city == null) {
//                throw new RuntimeException("City not found for ID: " + busRouteDto.getCityId());
//            }
//
//            BusRoutes busRoute = BusRoutes.builder()
//                    .routeVariant(routeVariant)
//                    .city(city)
//                    .stopOrder(busRouteDto.getStopOrder())
//                    .fare(busRouteDto.getFare())
//                    .build();
//
//            busRoutesMap.put(busRouteDto.getStopOrder(), busRoute);
//        }
//
//        List<BusRoutes> busRoutes = new ArrayList<>(busRoutesMap.values());
//        busRouteRepository.saveAll(busRoutes);
//
//        // Fetch all required boarding points in one batch
//        Set<Integer> boardingPointIds = routeVariantDto.getBusRoutesDto().stream()
//                .flatMap(busRouteDto -> busRouteDto.getBoardingPointTimeDto().stream())
//                .map(BoardingPointTimeDto::getBoardingPointId)
//                .collect(Collectors.toSet());
//
//        Map<Integer, BoardingPoints> boardingPointsMap = boardingPointsRepository.findAllById(boardingPointIds).stream()
//                .collect(Collectors.toMap(BoardingPoints::getId, bp -> bp));
//
//        // Set the first boarding time
//        LocalTime lastBoardingTime = routeVariantDto.getStartBoardingTime() != null
//                ? LocalTime.parse(routeVariantDto.getStartBoardingTime()) : null;
//
//        List<BoardingPointTime> allBoardingPointTimes = new ArrayList<>();
//
//        for (BusRoutes busRoute : busRoutes) {
//            BusRouteDto currentBusRouteDto = routeVariantDto.getBusRoutesDto().stream()
//                    .filter(r -> r.getCityId().equals(busRoute.getCity().getId()))
//                    .findFirst()
//                    .orElseThrow(() -> new RuntimeException("No boarding points found for city ID: " + busRoute.getCity().getId()));
//
//            for (BoardingPointTimeDto boardingPointTimeDto : currentBusRouteDto.getBoardingPointTimeDto()) {
//                BoardingPoints boardingPoint = boardingPointsMap.get(boardingPointTimeDto.getBoardingPointId());
//                if (boardingPoint == null) {
//                    throw new RuntimeException("Boarding Point not found for ID: " + boardingPointTimeDto.getBoardingPointId());
//                }
//                lastBoardingTime = incrementBoardingTime(lastBoardingTime, boardingPointTimeDto.getTimeInterval());
//                BoardingPointTime boardingPointTime = BoardingPointTime.builder()
//                        .busRoute(busRoute)
//                        .boardingPoint(boardingPoint)
//                        .boardingTime(lastBoardingTime)
//                        .build();
//                allBoardingPointTimes.add(boardingPointTime);
//
//                // Update the last boarding time
//              //  lastBoardingTime = incrementBoardingTime(lastBoardingTime, boardingPointTimeDto.getTimeInterval());
//            }
//        }
//
//        boardingPointTimeRepository.saveAll(allBoardingPointTimes);
//    }
//
//    private LocalTime incrementBoardingTime(LocalTime currentBoardingTime, Integer timeInterval) {
//        if (currentBoardingTime == null) {
//            throw new IllegalArgumentException("Current boarding time cannot be null");
//        }
//        return currentBoardingTime.plusMinutes(Optional.ofNullable(timeInterval).orElse(0));
//    }

}