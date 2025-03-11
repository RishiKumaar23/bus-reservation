package com.example.busReservation.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seat_availability")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    @CollectionTable(name = "window_seats", joinColumns = @JoinColumn(name = "seat_id"))
    @Column(name = "window_seat_name")
    private Set<String> windowSeats;

    @ElementCollection
    @CollectionTable(name = "other_seats", joinColumns = @JoinColumn(name = "seat_id"))
    @Column(name = "other_seat_name")
    private Set<String> otherSeats;

    @Column(name = "seat_capacity")
    private Integer seatCapacity;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusDetails busDetails;

    @Column(name = "fare")
    @Min(value = 0, message = "Fare must be at least 0")
    private BigDecimal fare;

    @OneToOne(mappedBy = "seat")
    private Booking booking;

    @Builder.Default
    @Column(name = "is_booked")
    private Boolean isBooked = false;

}
