package com.example.busReservation.Service;

import com.example.busReservation.Bean.BusOwnerBean;
import com.example.busReservation.Dto.BusOwnerDto;
import com.example.busReservation.Entity.BusOwner;
import com.example.busReservation.Repository.BusOwnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusOwnerService {
    private final BusOwnersRepository busOwnersRepository;

    private BusOwnerDto entityTODto(BusOwner busOwner) {
        return BusOwnerDto.builder().id(busOwner.getId()).OwnerName(busOwner.getOwnerName()).noOfBuses(busOwner.getNoOfBuses())
                .emailId(busOwner.getEmailId())
                .contactNo(busOwner.getContactNo())
                .build();
    }

    public void saveBusOwnerDetails(BusOwnerDto busOwnerDto) {

        List<BusOwnerBean> duplicateName = busOwnersRepository.findOwnerName(busOwnerDto.getId(), busOwnerDto.getCompanyName(), busOwnerDto.getEmailId(), busOwnerDto.getContactNo());
        for (BusOwnerBean existingName : duplicateName) {
            if (existingName.getCompanyName().equals(busOwnerDto.getCompanyName())) {
                throw new RuntimeException("Company Name already exists.");
            }
            if (existingName.getEmailId().equals(busOwnerDto.getEmailId())) {
                throw new RuntimeException("Email ID already exists.");
            }
            if (existingName.getContactNo().equals(busOwnerDto.getContactNo())) {
                throw new RuntimeException("Contact Number already exists.");
            }
        }
        BusOwner busOwner = BusOwner.builder()
                .companyName(busOwnerDto.getCompanyName())
                .ownerName(busOwnerDto.getOwnerName())
                .noOfBuses(busOwnerDto.getNoOfBuses())
                .emailId(busOwnerDto.getEmailId())
                .contactNo(busOwnerDto.getContactNo())
                .build();
        if (busOwnerDto.getId() != null) {
            busOwnersRepository.findByOwnerId(busOwnerDto.getId()).orElseThrow(() -> new RuntimeException("owner id is wrong"));
            busOwner.setId(busOwner.getId());
        }
        busOwnersRepository.save(busOwner);
    }

    public List<BusOwnerDto> getAllOwners() {
        return busOwnersRepository.findAll().stream().map(this::entityTODto).toList();
    }
    public BusOwnerDto getOwnerById(Integer id){
       Optional<BusOwner> busOwner=busOwnersRepository.findByOwnerId(id);
       return busOwner.map(this::entityTODto).orElse(null);
    }
    public void deleteOwnerById(Integer id){
        BusOwner busOwner=busOwnersRepository.findByOwnerId(id).orElseThrow(()->new RuntimeException("owner id doesnot found"));
        busOwnersRepository.delete(busOwner);
    }
}


