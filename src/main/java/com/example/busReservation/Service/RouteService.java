package com.example.busReservation.Service;

import com.example.busReservation.Dto.PickUpPointRequestDto;
import com.example.busReservation.Dto.PickUpPointsDto;
import com.example.busReservation.Dto.RouteDTo;
import com.example.busReservation.Dto.RouteVariantDto;
import com.example.busReservation.Entity.City;
import com.example.busReservation.Entity.PickUpPoints;
import com.example.busReservation.Entity.Route;
import com.example.busReservation.Entity.RouteVariant;
import com.example.busReservation.Enum.Status;
import com.example.busReservation.Repository.CityRepository;
import com.example.busReservation.Repository.PickUpPointsRepository;
import com.example.busReservation.Repository.RouteRepository;
import com.example.busReservation.Repository.RouteVariantRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final CityRepository cityRepository;
    private final RouteVariantRepository routeVariantRepository;
    private final PickUpPointsRepository pickUpPointsRepository;

    private RouteDTo entityToDto(Route route) {
        return RouteDTo.builder().id(route.getId())

                .sourceCity(route.getSourceCity())
                .destinationCity(route.getDestinationCity())
                .distanceKm(route.getDistanceKm())
                .status(route.getStatus().name()).build();
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
        Optional<Route> route = routeRepository.findByRouteId(id);
        return route.map(this::entityToDto).orElse(null);
    }

    public void deleteById(Integer id) {
        Route route = routeRepository.findByRouteId(id).orElseThrow(() -> new RuntimeException("route id  is not found for delete"));
        routeRepository.delete(route);
    }


    public void createAndUpdateRouteVariant(RouteVariantDto variantDto) {
        Route route = routeRepository.findById(variantDto.getRoute().getId()).orElseThrow(() -> new RuntimeException("route not found "));
        RouteVariant routeVariant = RouteVariant.builder().variantName(variantDto.getVariantName())
                .route(route).build();
        if (variantDto.getId() != null) {
            routeVariantRepository.findById(variantDto.getId()).orElseThrow(() -> new RuntimeException("variantID not found"));
            routeVariant.setId(variantDto.getId());
        }
        routeVariantRepository.save(routeVariant);
    }

    public void createAndUpdatePickUpPoints(PickUpPointsDto pickUpPointsDto) {
        RouteVariant routeVariant = routeVariantRepository.findById(pickUpPointsDto.getRouteVariantId())
                .orElseThrow(() -> new RuntimeException("Route Variant not found"));

        List<PickUpPoints> pickupPointsList = pickUpPointsDto.getPickUpPoints().stream().map(pickUpPointRequest -> {
            City city = cityRepository.findById(pickUpPointRequest.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found"));

            return PickUpPoints.builder()
                    .city(city)
                    .routeVariant(routeVariant)
                    .stopOrder(pickUpPointRequest.getStopOrder())
                    .fare(pickUpPointRequest.getFare())
                    .build();
        }).toList();

        pickUpPointsRepository.saveAll(pickupPointsList);
    }


    public List<PickUpPointsDto> getPickupPointsForRouteVariant(Integer routeVariantId) {
        List<PickUpPoints> pickupPoints = pickUpPointsRepository.findByRouteVariantIdOrderByStopOrder(routeVariantId);
        return pickupPoints.stream()
 .collect(Collectors.groupingBy(pickUpPoint -> pickUpPoint.getRouteVariant().getId()))
                .entrySet().stream()
                .map(entry -> PickUpPointsDto.builder()
                        .routeVariantId(entry.getKey())
                        .pickUpPoints(entry.getValue().stream()
                                .map(pickUpPoint -> PickUpPointRequestDto.builder()
                                        .cityId(pickUpPoint.getCity().getId())
                                        .stopOrder(pickUpPoint.getStopOrder())
                                        .fare(pickUpPoint.getFare())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

    }
}
