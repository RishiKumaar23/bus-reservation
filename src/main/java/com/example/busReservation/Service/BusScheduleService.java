package com.example.busReservation.Service;

import com.example.busReservation.Bean.BusSearchBean;
import com.example.busReservation.Dto.BoardingPointTimeDto;
import com.example.busReservation.Dto.BusRouteDto;
import com.example.busReservation.Dto.BusSchedulesDto;
import com.example.busReservation.Dto.BusSearchDto;
import com.example.busReservation.Entity.*;
import com.example.busReservation.Repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Data
@RequiredArgsConstructor
public class BusScheduleService {
    private final BusScheduleRepository scheduleRepository;
    private final BusDetailsRepository busDetailsRepository;
    private final RouteRepository routeRepository;
    private final RouteVariantRepository routeVariantRepository;
    private final CityRepository cityRepository;
    private final BoardingRepository boardingPointsRepository;
    private final BusRouteRepository busRouteRepository;
    private final BoardingPointTimeRepository boardingPointTimeRepository;

    private BusSchedulesDto convertTODto(BusSearchBean busSearchBean) {
        return BusSchedulesDto.builder()
                .id(busSearchBean.getId())
                .busDetails(BusDetails.builder().id(busSearchBean.getBusId()).build())
                .departureTime(busSearchBean.getDepartureTime())
                .arrivalTime(busSearchBean.getArrivalTime())
                .travelTime(busSearchBean.getTravelHours())
                .availableSeats(busSearchBean.getAvailableSeats())
                .route(Route.builder().id(busSearchBean.getRouteId()).build())
                .SeatIds(busSearchBean.getSeatIds())
                .SeatNames(busSearchBean.getSeatNames())
                .travelDate(busSearchBean.getTravelDate())
                .availableSeatIds(busSearchBean.getAvailableSeatIds())
                .availableSeatNames(busSearchBean.getAvailableSeatNames())
                .build();
    }

    public void saveBusSchedules(BusSchedulesDto busSchedulesDto) {
        BusDetails busDetails = busDetailsRepository.findById(busSchedulesDto.getBusDetails().getId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        Route route = routeRepository.findById(busSchedulesDto.getRoute().getId())
                .orElseThrow(() -> new RuntimeException("Route not found"));
        RouteVariant routeVariant = routeVariantRepository.findById(busSchedulesDto.getRouteVariant().getId())
                .orElseThrow(() -> new RuntimeException("RouteVariant not found"));

        BusSchedules busSchedules = BusSchedules.builder()
                .departureTime(busSchedulesDto.getDepartureTime())
                .travelDates(busSchedulesDto.getTravelDates())
                .route(route)
                .routeVariant(routeVariant)
                .busDetails(busDetails)
                .build();

        Set<Integer> cityIds = busSchedulesDto.getBusRoutesDto().stream()
                .map(BusRouteDto::getCityId)
                .collect(Collectors.toSet());

        Map<Integer, City> cityMap = cityRepository.findAllById(cityIds).stream()
                .collect(Collectors.toMap(City::getId, city -> city));

        TreeMap<Integer, BusRoutes> busRoutesMap = new TreeMap<>();

        for (BusRouteDto busRouteDto : busSchedulesDto.getBusRoutesDto()) {
            City city = cityMap.get(busRouteDto.getCityId());
            if (city == null) {
                throw new RuntimeException("City not found for ID: " + busRouteDto.getCityId());
            }

            BusRoutes busRoute = BusRoutes.builder()
                    .routeVariant(routeVariant)
                    .city(city)
                    .stopOrder(busRouteDto.getStopOrder())
                    .fare(busRouteDto.getFare())
                    .build();

            busRoutesMap.put(busRouteDto.getStopOrder(), busRoute);
        }

        List<BusRoutes> busRoutes = new ArrayList<>(busRoutesMap.values());
        busRouteRepository.saveAll(busRoutes);

        // Fetch all required boarding points in one batch
        Set<Integer> boardingPointIds = busSchedulesDto.getBusRoutesDto().stream()
                .flatMap(busRouteDto -> busRouteDto.getBoardingPointTimeDto().stream())
                .map(BoardingPointTimeDto::getBoardingPointId)
                .collect(Collectors.toSet());

        Map<Integer, BoardingPoints> boardingPointsMap = boardingPointsRepository.findAllById(boardingPointIds).stream()
                .collect(Collectors.toMap(BoardingPoints::getId, bp -> bp));


        LocalTime lastBoardingTime = busSchedulesDto.getDepartureTime() != null
                ? busSchedulesDto.getDepartureTime() : null;

        List<BoardingPointTime> allBoardingPointTimes = new ArrayList<>();
        LocalTime finalArrivalTime = lastBoardingTime;
        for (BusRoutes busRoute : busRoutes) {
            BusRouteDto currentBusRouteDto = busSchedulesDto.getBusRoutesDto().stream()
                    .filter(r -> r.getCityId().equals(busRoute.getCity().getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No boarding points found for city ID: " + busRoute.getCity().getId()));

            for (BoardingPointTimeDto boardingPointTimeDto : currentBusRouteDto.getBoardingPointTimeDto()) {
                BoardingPoints boardingPoint = boardingPointsMap.get(boardingPointTimeDto.getBoardingPointId());
                if (boardingPoint == null) {
                    throw new RuntimeException("Boarding Point not found for ID: " + boardingPointTimeDto.getBoardingPointId());
                }
                lastBoardingTime = incrementBoardingTime(lastBoardingTime, boardingPointTimeDto.getTimeInterval());
                BoardingPointTime boardingPointTime = BoardingPointTime.builder()
                        .busRoute(busRoute)
                        .boardingPoint(boardingPoint)
                        .boardingTime(lastBoardingTime)
                        .build();
                allBoardingPointTimes.add(boardingPointTime);

                finalArrivalTime = lastBoardingTime;
            }
        }
        double totalTravellingHours=calculateTravelHours(busSchedulesDto.getDepartureTime(),finalArrivalTime);
        busSchedules.setArrivalTime(finalArrivalTime);
        busSchedules.setTravelTime(totalTravellingHours);
        boardingPointTimeRepository.saveAll(allBoardingPointTimes);


        if (busSchedulesDto.getId() != null) {
            scheduleRepository.findById(busSchedulesDto.getId()).orElseThrow(() -> new RuntimeException("schedules id not found for update"));
            busSchedules.setId(busSchedulesDto.getId());
        }
        scheduleRepository.save(busSchedules);
    }

    public List<BusSchedulesDto> availableBuses(BusSearchDto busSearchDto) {
        if ((busSearchDto.getCustomStartTime() != null && busSearchDto.getCustomEndTime() == null) ||
                (busSearchDto.getCustomStartTime() == null && busSearchDto.getCustomEndTime() != null)) {
            throw new IllegalArgumentException("Both customStartTime and customEndTime must be provided together.");
        }
        List<BusSearchBean> scheduleDetails = scheduleRepository.findBuses(
                busSearchDto.getSourceCityId(),
                busSearchDto.getDestinationCityId(),
                busSearchDto.getTravelDate(),
                busSearchDto.getBusType(),
                busSearchDto.getSeatType(),
                busSearchDto.getCustomStartTime(),
                busSearchDto.getCustomEndTime(),
                busSearchDto.getFilterBy() != null ? busSearchDto.getFilterBy().name() : "DEPARTURE"
        );

        for (BusSearchBean bus : scheduleDetails) {
            System.out.println("Bus ID: " + bus.getId() + ", Available Seats: " + bus.getAvailableSeats());
        }

        if (scheduleDetails.isEmpty()) {
            throw new RuntimeException("No buses available for the selected criteria.");
        }
        return scheduleDetails.stream().map(this::convertTODto).collect(Collectors.toList());
    }

    private LocalTime incrementBoardingTime(LocalTime currentBoardingTime, Integer timeInterval) {
        if (currentBoardingTime == null) {
            throw new IllegalArgumentException("Current boarding time cannot be null");
        }
        return currentBoardingTime.plusMinutes(Optional.ofNullable(timeInterval).orElse(0));
    }

    private double calculateTravelHours(LocalTime departureTime, LocalTime arrivalTime) {
        if (departureTime == null || arrivalTime == null) {
            throw new IllegalArgumentException("departure and arrival time cannot be null");
        }
        return (double) java.time.Duration.between(departureTime, arrivalTime).toMinutes() / 60;
    }
}
