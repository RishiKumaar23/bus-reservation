package com.example.busReservation.Service;

import com.example.busReservation.Dto.BookingRequestDto;
import com.example.busReservation.Entity.*;
import com.example.busReservation.Enum.BookingStatus;
import com.example.busReservation.Repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final SeatRepository seatRepository;
    private final UserRepository userDetailsRepository;
    private final BusDetailsRepository busDetailsRepository;
    private final BookingRepository bookingRepository;
    private final BoardingRepository boardingRepository;
    private final BusTravelDateRepository busTravelDateRepository;


    @Transactional
    public String bookSeat(BookingRequestDto bookingRequest) {
        UserDetails user = userDetailsRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found "));

        BusDetails busDetails = busDetailsRepository.findById(bookingRequest.getBusId())
                .orElseThrow(() -> new IllegalArgumentException("Bus not found"));

        Seat seat = seatRepository.findById(bookingRequest.getSeatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat not found "));

        BusTravelDate busTravelDate = busTravelDateRepository.findById(bookingRequest.getTravelDateId())
                .orElseThrow(() -> new IllegalArgumentException("No schedule found for travel date"));

        boolean seatAlreadyBooked = bookingRepository.countBookingsBySeatAndBusAndDate(
                bookingRequest.getSeatId(),
                bookingRequest.getBusId(),
                bookingRequest.getTravelDateId(),bookingRequest.getStatus()
        ) > 0;

        if (seatAlreadyBooked) {
            throw new IllegalStateException("Seat ID " + bookingRequest.getSeatId() + " is already booked for this date.");
        }

        BoardingPoints boardingPoint = boardingRepository.findById(bookingRequest.getBoardingPointId())
                .orElseThrow(() -> new IllegalArgumentException("Boarding point not found with ID: " + bookingRequest.getBoardingPointId()));

        Booking booking = Booking.builder()
                .user(user)
                .busDetails(busDetails)
                .seat(seat)
                .travelDate(busTravelDate)
                .bookingDate(LocalDate.now())
                .boardingPoints(boardingPoint)
                .status(bookingRequest.getStatus() != null ? bookingRequest.getStatus() : BookingStatus.BOOKED)
                .build();

        bookingRepository.save(booking);
        return "Seat booked successfully for travel date ID: " + bookingRequest.getTravelDateId();
    }


    @Transactional
    public String cancelBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        return "Booking cancelled successfully.";
    }
}
