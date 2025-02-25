package com.example.busReservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_owner")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusDetails busDetails;

    @Column(name = "no_of_window_seats")
    private Integer noOfWindowSeats;

    @Column(name = "no_of_other_seats")
    private Integer noOfOtherSeats;



}