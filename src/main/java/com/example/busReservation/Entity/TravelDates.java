package com.example.busReservation.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "travel_dates")
public class TravelDates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "travel_date", nullable = false)
    private LocalDate travelDate;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private BusDetails busDetails;

}
