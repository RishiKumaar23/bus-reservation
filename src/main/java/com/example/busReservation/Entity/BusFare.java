package com.example.busReservation.Entity;

import com.example.busReservation.Enum.SeatCategory;
import com.example.busReservation.Enum.SeatType;
import com.example.busReservation.Enum.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_fare")
public class BusFare {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "bus_id")
        private BusDetails busDetails;

        @ManyToOne
        @JoinColumn(name = "route_id")
        private Route route;

        @Enumerated(EnumType.STRING)
        @Column(name = "seat_type")
        private SeatType seatType;

        @Enumerated(EnumType.STRING)
        @Column(name = "seat_category")
        private SeatCategory seatCategory;

        @Column(name = "fare")
        @Min(value = 0, message = "Fare must be at least 0")
        private BigDecimal fare;

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        private Status status = Status.INACTIVE;
    }

