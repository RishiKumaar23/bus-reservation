package com.example.busReservation.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "route_variant")
public class RouteVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(mappedBy = "routeVariant")
     private List<PickUpPoints> pickUpPointsList;

    @Column(name = "variant_name")
    private String variantName;
}
