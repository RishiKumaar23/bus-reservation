package com.example.busReservation.Controller;

import com.example.busReservation.Dto.UserDto;
import com.example.busReservation.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UsersService userService;
    @PutMapping("/save")
    private ResponseEntity<?> saveBusDetails(@RequestBody UserDto usersDto) {
        try {
            userService.saveUsersDetails(usersDto);
            String message = (usersDto.getId() != null) ? "Users details updated" : "users details created";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
