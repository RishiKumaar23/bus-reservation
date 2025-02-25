package com.example.busReservation.Service;

import com.example.busReservation.Dto.CityDto;
import com.example.busReservation.Dto.CountryDto;
import com.example.busReservation.Dto.StateDto;
import com.example.busReservation.Entity.City;
import com.example.busReservation.Entity.Country;
import com.example.busReservation.Entity.State;
import com.example.busReservation.Repository.CityRepository;
import com.example.busReservation.Repository.CountryRepository;
import com.example.busReservation.Repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;


    public void createAndUpdateLocation(CountryDto countryDto) {

        Country duplicateCountryName = countryRepository.findByCountry(countryDto.getId(), countryDto.getCountryName());
        if (!Objects.isNull(duplicateCountryName)) {
            throw new RuntimeException("country name already exists");
        }
        Country country = Country.builder().countryName(countryDto.getCountryName()).build();
        if (Objects.nonNull(countryDto.getId())) {
            countryRepository.findByCountryId(countryDto.getId()).orElseThrow(() -> new RuntimeException("country Id is wrong"));
            country.setId(countryDto.getId());
        }
        List<State> stateList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(countryDto.getStates())) {
            // Validate States
            List<Integer> stateIds = countryDto.getStates().stream().map(StateDto::getId).filter(Objects::nonNull).toList();  // CHANGED (added filter to remove null IDs)
            List<String> stateNames = countryDto.getStates().stream().map(StateDto::getStateName).toList();

            List<State> existingStates = stateRepository.findByState(stateIds, stateNames);
            if (!CollectionUtils.isEmpty(existingStates)) {
                throw new RuntimeException("State name(s) already exist: " + existingStates.stream().map(State::getStateName).toList());  // CHANGED (better error message)
            }

            for (StateDto stateDto : countryDto.getStates()) {
                State state = State.builder().stateName(stateDto.getStateName()).country(country).build();

                if (Objects.nonNull(stateDto.getId())) {
                    stateRepository.findByStateId(stateDto.getId()).orElseThrow(() -> new RuntimeException("Invalid State ID"));
                    state.setId(stateDto.getId());
                }

                List<City> cityList = new ArrayList<>();

                if (!CollectionUtils.isEmpty(stateDto.getCity())) {
                    // Validate Cities
                    List<Integer> cityIds = stateDto.getCity().stream().map(CityDto::getId).filter(Objects::nonNull).toList();  // CHANGED (added filter to remove null IDs)
                    List<String> cityNames = stateDto.getCity().stream().map(CityDto::getCityName).toList();

                    List<City> existingCities = cityRepository.findCity(cityIds, cityNames);
                    if (!CollectionUtils.isEmpty(existingCities)) {
                        throw new RuntimeException("City name(s) already exist: " + existingCities.stream().map(City::getCityName).toList());  // CHANGED (better error message)
                    }

                    for (CityDto cityDto : stateDto.getCity()) {
                        City city = City.builder().cityName(cityDto.getCityName()).state(state).build();
                        if (Objects.nonNull(cityDto.getId())) {
                            cityRepository.findByCityId(cityDto.getId()).orElseThrow(() -> new RuntimeException("Invalid City ID"));
                            city.setId(cityDto.getId());
                        }
                        cityList.add(city);
                    }
                }

                state.setCity(cityList);
                stateList.add(state);
            }
        }

        country.setStates(stateList);
        countryRepository.save(country);
    }

    public void deleteCountry(Integer countryId) {
        Country existingCountry = countryRepository.findByCountryId(countryId).orElseThrow(() -> new RuntimeException("country with id not found"));
        countryRepository.delete(existingCountry);
    }
    public void deleteState(Integer Id) {
        State existingState = stateRepository.findByStateId(Id).orElseThrow(() -> new RuntimeException("state with id not found"));
        stateRepository.delete(existingState);
    }
    public void deleteCity(Integer Id) {
        City existingCity = cityRepository.findByCityId(Id).orElseThrow(() -> new RuntimeException("city with id not found"));
        cityRepository.delete(existingCity);
    }

}
