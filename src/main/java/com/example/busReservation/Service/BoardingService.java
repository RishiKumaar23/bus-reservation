package com.example.busReservation.Service;

import com.example.busReservation.Dto.BoardingPointsDto;
import com.example.busReservation.Entity.BoardingPoints;
import com.example.busReservation.Entity.City;
import com.example.busReservation.Repository.BoardingRepository;
import com.example.busReservation.Repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardingService {
    private final BoardingRepository boardingRepository;
    private final CityRepository cityRepository;

    public void createAndUpdateBoarding(BoardingPointsDto boardingDto) {

        City city = cityRepository.findById(boardingDto.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));


        BoardingPoints duplicateBoarding = boardingRepository.findByBoardingNameAndCity(boardingDto.getBoardingName(), city.getId());

        if (Objects.nonNull(duplicateBoarding) &&
                (boardingDto.getId() == null || !Objects.equals(duplicateBoarding.getId(), boardingDto.getId()))) {
            throw new RuntimeException("Boarding point already exists in this city");
        }
        BoardingPoints boardingPoint;
        if (Objects.nonNull(boardingDto.getId())) {

            boardingPoint = boardingRepository.findById(boardingDto.getId())
                    .orElseThrow(() -> new RuntimeException("Boarding Point not found"));
            boardingPoint.setBoardingName(boardingDto.getBoardingName());
            boardingPoint.setCity(city);
        } else {

            boardingPoint = BoardingPoints.builder()
                    .boardingName(boardingDto.getBoardingName())
                    .city(city)
                    .build();
        }

        boardingRepository.save(boardingPoint);
    }

    public void deleteBoardingPoint(Integer id) {
        BoardingPoints existingPoint=boardingRepository.findById(id).orElseThrow(()->new RuntimeException("Boarding point does not found "));
        boardingRepository.delete(existingPoint);
    }
}