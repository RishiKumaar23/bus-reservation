package com.example.busReservation.Service;

import com.example.busReservation.Dto.SeatDto;
import com.example.busReservation.Entity.Seat;
import com.example.busReservation.Repository.BusDetailsRepository;
import com.example.busReservation.Repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {
      private final BusDetailsRepository busRepository;
      private final SeatRepository seatRepository;


      public void saveAndUpdateSeats(SeatDto seatDto) {

            Integer totalSeats= seatDto.getNoOfWindowSeats() + seatDto.getNoOfOtherSeats();
           if(!totalSeats.equals(seatDto.getBus().getSeatCapacity())){
                 throw new RuntimeException("Seat count mismatch: The number of selected seats does not match the total seat capacity");
           }
            Seat seat=Seat.builder().noOfWindowSeats(seatDto.getNoOfWindowSeats())
                    .noOfOtherSeats(seatDto.getNoOfOtherSeats())
                    .busDetails(seatDto.getBus())
                    .build();
            if(seatDto.getId()!=null){
                  seatRepository.findBySeatId(seatDto.getId()).orElseThrow(()->new RuntimeException("seat id not found"));
                   seat.setId(seatDto.getId());
            }
            seatRepository.save(seat);
      }
}
