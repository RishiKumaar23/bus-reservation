package com.example.busReservation.Entity;

import com.example.busReservation.Enum.BusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_details")
public class BusDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bus_reg_no", unique = true, length = 10)
    private String busRegNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "bus_type")
    private BusType busType;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private BusOwner owner;

}

