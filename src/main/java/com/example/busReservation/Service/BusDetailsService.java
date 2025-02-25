package com.example.busReservation.Service;

import com.example.busReservation.Dto.BusDetailsDto;
import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Enum.BusType;
import com.example.busReservation.Repository.BusDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusDetailsService {
    private final BusDetailsRepository busRepository;

    private BusDetailsDto entityToDto(BusDetails busDetails) {
        return BusDetailsDto.builder()
                .id(busDetails.getId())
                .busRegNo(busDetails.getBusRegNo())
                .busType(busDetails.getBusType().name())
                .owner(busDetails.getOwner())
                .route(busDetails.getRoute())
                .departureTime(busDetails.getDepartureTime())
                .arrivalTime(busDetails.getArrivalTime())
                .seatCapacity(busDetails.getSeatCapacity())
                .build();
    }


    public void saveBusDetails(BusDetailsDto busDetailsDto) {

        BusDetails existingBus = busRepository.findByBusRegNo(busDetailsDto.getId(), busDetailsDto.getBusRegNo());
        if (!Objects.isNull(existingBus) && existingBus.getBusRegNo().equals(busDetailsDto.getBusRegNo())) {
            throw new RuntimeException("Bus with registration number " + busDetailsDto.getBusRegNo() + " already exists.");
        }

        // Save bus details
        BusDetails busDetails = BusDetails.builder()
                .busRegNo(busDetailsDto.getBusRegNo())
                .busType(BusType.valueOf(busDetailsDto.getBusType()))
                .route(busDetailsDto.getRoute())
                .owner(busDetailsDto.getOwner())
                .seatCapacity(busDetailsDto.getSeatCapacity())
                .build();


        if (busDetailsDto.getDepartureTime().isBefore(busDetailsDto.getArrivalTime())) {
            busDetails.setDepartureTime(busDetailsDto.getDepartureTime());
            busDetails.setArrivalTime(busDetailsDto.getArrivalTime());
        } else {
            throw new RuntimeException("arrival time  is after the departure time ");
        }
        ;
        if (busDetailsDto.getId() != null) {
            busRepository.findByBusId(busDetailsDto.getId()).orElseThrow(() -> new RuntimeException("Bus Not found"));
            busDetails.setId(busDetailsDto.getId());
        }
        busRepository.save(busDetails);
    }

    public List<BusDetailsDto> getAllBusDetails() {
        return busRepository.findAll().stream().map(this::entityToDto).toList();
    }

    public BusDetailsDto getBusById(Integer id) {
        Optional<BusDetails> busDetails = busRepository.findByBusId(id);
        return busDetails.map(this::entityToDto).orElse(null);
    }

    public void deleteById(Integer id) {
        BusDetails busDetails = busRepository.findByBusId(id).orElseThrow(() -> new RuntimeException("id  is not found for delete"));
        busRepository.delete(busDetails);
    }
}

