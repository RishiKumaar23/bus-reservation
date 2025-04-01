package com.example.busReservation.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_travel_dates")
public class BusTravelDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "travel_date")
    private LocalDate travelDate;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private BusSchedules busSchedule;

    @OneToMany(mappedBy = "travelDate",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Booking> bookingList;

}
