package com.example.busReservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "state_name")
    private String stateName;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @OneToMany(mappedBy = "state",cascade = CascadeType.ALL,orphanRemoval = false)
    private List<City> city;
}
