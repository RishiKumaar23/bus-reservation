package com.example.busReservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_schedules")
public class BusSchedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @ElementCollection
    @CollectionTable(name = "bus_travel_dates", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "travel_date")
    private Set<LocalDate> travelDates;

    @Column(name = "total_traveling_hours")
    private Double travelTime;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusDetails busDetails;

    @ManyToOne
    @JoinColumn(name ="route_id")
    private Route route;
}



