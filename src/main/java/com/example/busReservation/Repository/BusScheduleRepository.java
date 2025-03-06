package com.example.busReservation.Repository;

import com.example.busReservation.Bean.BusSearchBean;
import com.example.busReservation.Entity.BusSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BusScheduleRepository extends JpaRepository<BusSchedules, Integer> {

    @Query(value = "SELECT bs.id as id, bs.bus_id as busId, bs.departure_time as departureTime, " +
            "bs.arrival_time as arrivalTime, bs.total_traveling_hours as travelHours, bs.route_id as routeId, " +
            "td.travel_date as travelDate " +
            "from bus_schedules bs " +
            "join route r ON bs.route_id = r.id " +
            "join bus_travel_dates td ON td.schedule_id = bs.id " +
            "join bus_details bd on bs.bus_id=bd.id " +
            "where r.source_city_id = :sourceCityId " +
            "and r.destination_city_id = :destinationCityId " +
            "and td.travel_date = :travelDate " +
            " AND (:travelDate > CURRENT_DATE OR bs.departure_time > AddTime(CURRENT_TIME, '01:00:00'))  " +
            "  AND (:busType is null OR bd.bus_type = :busType) " +
            "  AND (:seatType is null OR bd.seat_type = :seatType) " +
            "OR ((" +
            "    (:filterBy = 'DEPARTURE' AND (:customStartTime IS NULL OR :customEndTime IS NULL OR " +
            "    (bs.departure_time BETWEEN :customStartTime AND :customEndTime))) " +
            ") OR (" +
            "    (:filterBy = 'ARRIVAL' AND (:customStartTime IS NULL OR :customEndTime IS NULL OR " +
            "    (bs.arrival_time BETWEEN :customStartTime AND :customEndTime))) " +
            ")) " +
            "  order By bs.departure_time ASC  ", nativeQuery = true)
    List<BusSearchBean> findBuses(@Param("sourceCityId") Integer sourceCityId,
                                  @Param("destinationCityId") Integer destinationCityId,
                                  @Param("travelDate") LocalDate travelDate,
                                  @Param("busType") String busType,
                                  @Param("seatType") String seatType,
                                  @Param("customStartTime") String customStartTime,
                                  @Param("customEndTime") String customEndTime,
                                  @Param("filterBy") String filterBy);


}