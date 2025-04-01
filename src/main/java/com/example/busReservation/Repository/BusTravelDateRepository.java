package com.example.busReservation.Repository;

import com.example.busReservation.Entity.BusTravelDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BusTravelDateRepository extends JpaRepository<BusTravelDate,Integer> {
    @Query(value = "SELECT btd.* FROM bus_travel_dates btd " +
            "JOIN bus_schedules bs ON btd.schedule_id = bs.id " +
            "WHERE bs.bus_id = :busId AND btd.id = :travelDateId", nativeQuery = true)
    Optional<BusTravelDate> findByBusDetailsIdAndTravelDate(@Param("busId") Integer busId,
                                                         @Param("travelDateId") Integer travelDateId);

}
