package com.example.busReservation.Entity;

import com.example.busReservation.Enum.SeatCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
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

    @Column(name = "seat_name",unique = true)
    private String seatName;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_category")
    private SeatCategory seatCategory;


    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusDetails busDetails;

    @OneToOne(mappedBy = "seat")
    private Booking booking;
}
