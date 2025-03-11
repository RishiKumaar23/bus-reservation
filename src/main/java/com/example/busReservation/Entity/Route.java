package com.example.busReservation.Entity;


import com.example.busReservation.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "source_city_id")
    private City sourceCity;

    @ManyToOne
    @JoinColumn(name = "destination_city_id")
    private City destinationCity;

    @Column(name = "distance_km")
    private Integer distanceKm;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.INACTIVE;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<RouteVariant> routeVariants;

    @OneToMany(mappedBy = "route",cascade = CascadeType.ALL)
    private List<BusSchedules> busSchedules;

}


