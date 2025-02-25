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
@Table (name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "country_name")
    private String countryName;
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL,orphanRemoval =false)
    private List<State> states;
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
    private List<UserDetails> userDetails;
}
