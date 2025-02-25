package com.example.busReservation.Service;

import com.example.busReservation.Dto.RouteDTo;
import com.example.busReservation.Entity.Route;
import com.example.busReservation.Enum.Status;
import com.example.busReservation.Repository.RouteRepository;
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
}