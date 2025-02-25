package com.example.busReservation.Service;

import com.example.busReservation.Bean.UsersBean;
import com.example.busReservation.Dto.UserDto;
import com.example.busReservation.Entity.UserDetails;
import com.example.busReservation.Enum.Status;
import com.example.busReservation.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUsersDetails(UserDto usersDto) {

        List<UsersBean> duplicateUsers = userRepository.findUsersDetails(
                usersDto.getAdharId(), usersDto.getEmail(), usersDto.getPhone());

        if (!duplicateUsers.isEmpty()) {
            throw new RuntimeException("A user with the provided adharId, email, or phone already exists.");
        }
        UserDetails userDetails = UserDetails.builder()
                .name(usersDto.getName())
                .adharId(usersDto.getAdharId())
                .age(usersDto.getAge())
                .gender(usersDto.getGender())
                .email(usersDto.getEmail())
                .phone(usersDto.getPhone())
                .nationality(usersDto.getNationality())
                .country(usersDto.getCountry())
                .status(Status.valueOf(usersDto.getStatus()))
                .build();
        if(usersDto.getId() != null){
            userRepository.findByUserId(usersDto.getId()).orElseThrow(()->new RuntimeException("user id not found"));
            userDetails.setId(userDetails.getId());
        }
        userRepository.save(userDetails);
    }
}
