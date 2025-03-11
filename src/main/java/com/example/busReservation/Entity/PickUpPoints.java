package com.example.busReservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pickup_points")
public class PickUpPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "route_variant_id")
    private RouteVariant routeVariant;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "stop_order")
    private Integer stopOrder;

    @Column(name = "fare")
    private Double fare;

}
