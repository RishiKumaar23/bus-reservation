package com.example.busReservation.Service;

import com.example.busReservation.Dto.BookingDto;
import com.example.busReservation.Entity.Booking;
import com.example.busReservation.Entity.BusSchedules;
import com.example.busReservation.Entity.Seat;
import com.example.busReservation.Entity.UserDetails;
import com.example.busReservation.Repository.BookingRepository;
import com.example.busReservation.Repository.BusScheduleRepository;
import com.example.busReservation.Repository.SeatRepository;
import com.example.busReservation.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final BusScheduleRepository busScheduleRepository;
    private final BookingRepository bookingRepository;
    public Booking createBooking(BookingDto bookingDto) {

        UserDetails user = userRepository.findById(bookingDto.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User  not found"));

        BusSchedules busSchedule = busScheduleRepository.findById(bookingDto.getBusSchedule().getId())
                .orElseThrow(() -> new RuntimeException("Bus schedule not found"));

        Seat seat = seatRepository.findById(bookingDto.getSeat().getId()).orElseThrow(() -> new RuntimeException("Seat not found"));
        if (seat.getIsBooked()) {
            throw new RuntimeException("Seat is already booked");
        }
        Booking booking = Booking.builder()
                .busSchedule(busSchedule)
                .seat(seat)
                .bookingDate(LocalDate.now())
                .build();
        seat.setIsBooked(true);
        seat.setBooking(booking);
        bookingRepository.save(booking);
        seatRepository.save(seat);

        return booking;
    }


    }
