package com.example.busReservation.Service;

import com.example.busReservation.Dto.BusFareDto;
import com.example.busReservation.Entity.BusFare;
import com.example.busReservation.Enum.SeatCategory;
import com.example.busReservation.Enum.SeatType;
import com.example.busReservation.Enum.Status;
import com.example.busReservation.Repository.BusFareRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@Data
@RequiredArgsConstructor
public class BusFareService {
    private final BusFareRepository busFareRepository;
    private final MessageProperties messageProperties;

    private BusFareDto entityToDto(BusFare busFare){
        return BusFareDto.builder().id(busFare.getId())
        .busDetails(busFare.getBusDetails())
                .route(busFare.getRoute())
                .seatType(busFare.getSeatType())
                .seatCategory(busFare.getSeatCategory())
                .fare(busFare.getFare())
                .status(String.valueOf(busFare.getStatus())).build();
    }

    public void saveBusFare(BusFareDto busFareDto) {
        if (busFareDto.getBusDetails() == null || busFareDto.getBusDetails().getBusType() == null) {
            throw new IllegalArgumentException("Bus details and bus type must be provided.");
        }

        SeatType seatType = busFareDto.getSeatType();
        SeatCategory seatCategory =busFareDto.getSeatCategory();
        BigDecimal fare = busFareDto.getFare();

        if (fare == null || fare.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Fare must be a non-negative value.");
        }

        switch (seatType) {
            case SLEEPER:

                switch (seatCategory) {
                    case WINDOW:
                        if (fare.compareTo(new BigDecimal("100")) < 0) {
                            throw new IllegalArgumentException("For a SLEEPER bus with a WINDOW seat, the fare must be at least 100.");
                        }
                        break;
                    case AISLE:
                        if (fare.compareTo(new BigDecimal("80")) < 0) {
                            throw new IllegalArgumentException("For a SLEEPER bus with an AISLE seat, the fare must be at least 80.");
                        }
                        break;

                }
                break;

            case SEMISLEEPER:
                switch (seatCategory) {
                    case WINDOW:
                        if (fare.compareTo(new BigDecimal("70")) < 0) {
                            throw new IllegalArgumentException("For a SEMISLEEPER bus with a WINDOW seat, the fare must be at least 70.");
                        }
                        break;
                    case AISLE:
                        if (fare.compareTo(new BigDecimal("50")) < 0) {
                            throw new IllegalArgumentException("For a SEMISLEEPER bus with an AISLE seat, the fare must be at least 50.");
                        }
                        break;

                }
                break;
        }
        BusFare busFare = BusFare.builder()
                .busDetails(busFareDto.getBusDetails())
                .route(busFareDto.getRoute())
                .seatType(seatType)
                .seatCategory(seatCategory)
                .fare(fare)
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


