package com.example.busReservation.Service;

import com.example.busReservation.Dto.BusFareDto;
import com.example.busReservation.Entity.BusFare;
import com.example.busReservation.Enum.Status;
import com.example.busReservation.Repository.BusFareRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class BusFareService {
    private final BusFareRepository busFareRepository;
    private final MessageProperties messageProperties;

    private BusFareDto entityToDto(BusFare busFare){
        return BusFareDto.builder().id(busFare.getId())
                .fare(busFare.getFare())
                .seat(busFare.getSeat())
                .status(String.valueOf(busFare.getStatus())).build();
    }

    public void saveBusFare(BusFareDto busFareDto) {

        BusFare busFare = BusFare.builder()
                .seat(busFareDto.getSeat())
                .fare(busFareDto.getFare())
                .status(Status.valueOf(busFareDto.getStatus()))
                .build();

        if (busFareDto.getId() != null) {
            busFareRepository.findById(busFareDto.getId()).orElseThrow(() -> new RuntimeException("busFare id is  wrong"));
            busFare.setId(busFareDto.getId());
        }
        busFareRepository.save(busFare);
    }

    public Object getAllBusFare() {
        return busFareRepository.findAll().stream().map(this::entityToDto).toList();
    }

    public BusFareDto getBusFarById(Integer id) {
        Optional<BusFare> busFare=busFareRepository.findById(id);
        return busFare.map(this::entityToDto).orElse(null);
    }
    public void deleteByFareId(Integer id){
        BusFare busFare=busFareRepository.findById(id).orElseThrow(()->new RuntimeException(messageProperties.message("bus.fare.delete")));
        busFareRepository.delete(busFare);
    }
}


