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
@Table(name = "vehicle_owner")
public class BusOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_name",  length = 30)
    private String companyName;

    @Column(name = "owner_name",  length = 30)
    private String ownerName;

    @Column(name = "no_of_buses" )
    private Integer noOfBuses;

    @Column(name = "email_id", length = 50, unique = true)
    private String emailId;

    @Column(name = "contact_no", length = 15)
    private String contactNo;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusDetails> buses;
}

