package com.example.busReservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "boarding_point_time")
public class BoardingPointTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bus_route_id")
    private BusRoutes busRoute;

    @ManyToOne
    @JoinColumn(name = "boarding_point_id")
    private BoardingPoints boardingPoint;

    @Column(name = "boarding_time")
    private LocalTime boardingTime;
}
