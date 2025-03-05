package com.example.busReservation.Service;

import com.example.busReservation.Dto.SeatDto;
import com.example.busReservation.Entity.Seat;
import com.example.busReservation.Enum.SeatCategory;
import com.example.busReservation.Enum.SeatType;
import com.example.busReservation.Repository.BusDetailsRepository;
import com.example.busReservation.Repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final BusDetailsRepository busRepository;
    private final SeatRepository seatRepository;

    public void createAndUpdateSeats(SeatDto seatDto) {

        Integer windowSeats= seatDto.getWindowSeats().size();
        Integer otherSeats=seatDto.getOtherSeats().size();
        Integer totalSeats=windowSeats+otherSeats;
        Seat seat=Seat.builder().otherSeats(seatDto.getOtherSeats())
                .windowSeats(seatDto.getWindowSeats())
                .busDetails(seatDto.getBusDetails())
                .seatCapacity(totalSeats)
                .fare(seatDto.getFare())
                .booking(seatDto.getBooking())
                .isBooked(seatDto.isBooked())
                .build();

        if(seatDto.getId()!=null){
            seatRepository.findBySeatId(seatDto.getId()).orElseThrow(()->new RuntimeException("Id not found for update"));
            seat.setId(seatDto.getId());
        }seatRepository.save(seat);
    }
}
