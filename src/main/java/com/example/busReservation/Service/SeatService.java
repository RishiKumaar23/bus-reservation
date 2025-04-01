package com.example.busReservation.Service;

import com.example.busReservation.Dto.SeatDto;
import com.example.busReservation.Entity.BusDetails;
import com.example.busReservation.Entity.Seat;
import com.example.busReservation.Enum.SeatCategory;
import com.example.busReservation.Repository.BusDetailsRepository;
import com.example.busReservation.Repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final BusDetailsRepository busRepository;

    public void createSeats(SeatDto seatDto) {


        if (seatDto.getSeats() == null || seatDto.getSeats().isEmpty()) {
            throw new IllegalArgumentException("Seats list cannot be empty");
        }


        BusDetails busDetails = busRepository.findById(seatDto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + seatDto.getBusId()));


        List<Seat> seats = seatDto.getSeats().stream()
                .map(entry -> Seat.builder()
                        .seatCategory(SeatCategory.valueOf(entry.getSeatCategory()))
                        .seatName(entry.getSeatName())
                        .busDetails(busDetails)

                        .build())
                .collect(Collectors.toList());


        seatRepository.saveAll(seats);
    }

    public void updateSeatCategories(SeatDto seatDto) {
        if (seatDto.getSeats() == null || seatDto.getSeats().isEmpty()) {
            throw new IllegalArgumentException("Seats list cannot be empty");
        }

        List<Seat> seatsToUpdate = seatDto.getSeats().stream()
                .map(entry -> {

                    Seat existingSeat = seatRepository.findById(entry.getId())
                            .orElseThrow(() -> new RuntimeException("Seat ID not found: " + entry.getId()));


                    existingSeat.setSeatCategory(SeatCategory.valueOf(entry.getSeatCategory()));

                    return existingSeat;
                })
                .collect(Collectors.toList());


        seatRepository.saveAll(seatsToUpdate);
    }

}
