package com.example.busReservation.Service;

import com.example.busReservation.Bean.BusSearchBean;
import com.example.busReservation.Dto.BusSchedulesDto;
import com.example.busReservation.Dto.BusSearchDto;
import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Entity.BusSchedules;
import com.example.busReservation.Entity.Route;
import com.example.busReservation.Enum.FilterBy;
import com.example.busReservation.Repository.BusDetailsRepository;
import com.example.busReservation.Repository.BusScheduleRepository;
import com.example.busReservation.Repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BusScheduleService {
private final BusScheduleRepository scheduleRepository;
private final BusDetailsRepository busDetailsRepository;
private final RouteRepository routeRepository;

    private BusSchedulesDto convertTODto(BusSearchBean busSearchBean) {
        return BusSchedulesDto.builder()
                .id(busSearchBean.getId())
                .busDetails(BusDetails.builder().id(busSearchBean.getBusId()).build())
                .departureTime(busSearchBean.getDepartureTime())
                .arrivalTime(busSearchBean.getArrivalTime())
                .travelTime(busSearchBean.getTravelHours())
                .route(Route.builder().id(busSearchBean.getRouteId()).build())
                .build();
    }

    public void saveBusSchedules(BusSchedulesDto busSchedulesDto) {
        BusDetails busDetails = busDetailsRepository.findById(busSchedulesDto.getBusDetails().getId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        Route route = routeRepository.findById(busSchedulesDto.getRoute().getId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        BusSchedules busSchedules = BusSchedules.builder()
                .departureTime(busSchedulesDto.getDepartureTime())
                .arrivalTime(busSchedulesDto.getArrivalTime())
                .travelTime(busSchedulesDto.getTravelTime())
                .travelDates(busSchedulesDto.getTravelDates())
                .route(route)
                .busDetails(busDetails)
                .build();
     if(busSchedulesDto.getId()!=null){
         scheduleRepository.findById(busSchedulesDto.getId()).orElseThrow(()->new RuntimeException("schedules id not found for update"));
         busSchedules.setId(busSchedulesDto.getId());
    }scheduleRepository.save(busSchedules);
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
        if (scheduleDetails.isEmpty()) {
            throw new RuntimeException("No buses available for the selected criteria.");
        }
        return scheduleDetails.stream().map(this::convertTODto).collect(Collectors.toList());
    }
}

