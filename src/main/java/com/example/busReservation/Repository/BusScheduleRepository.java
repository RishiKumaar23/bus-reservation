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
            "bs.arrival_time as arrivalTime, bs.total_traveling_hours as travelHours, " +
            "bs.route_id as routeId, td.travel_date as travelDate, " +
            "COUNT(DISTINCT CASE WHEN b.id IS NULL OR b.status = 'CANCELLED' THEN s.id END) as availableSeats, " +
            "GROUP_CONCAT(distinct case when b.id is null OR b.status = 'CANCELLED' then s.id end ORDER BY s.id ASC) as availableSeatIds, " +
            "GROUP_CONCAT(distinct case when b.id is null OR b.status = 'CANCELLED' then s.seat_name end ORDER BY s.seat_name ASC) as availableSeatNames " +
            "FROM bus_schedules bs " +
            "JOIN route r ON bs.route_id = r.id " +
            "JOIN bus_travel_dates td ON td.schedule_id = bs.id " +
            "JOIN bus_details bd ON bs.bus_id = bd.id " +
            "LEFT JOIN seat_availability s ON s.bus_id = bd.id " +
            "LEFT JOIN bookings b ON b.seat_id = s.id AND b.travel_date_id = td.id " +
            "AND (b.status IS NULL OR b.status != 'BOOKED') " +
            "WHERE r.source_city_id = :sourceCityId " +
            "AND r.destination_city_id = :destinationCityId " +
            "AND td.travel_date = :travelDate " +
            "AND (:travelDate > CURRENT_DATE OR bs.departure_time > ADDTIME(CURRENT_TIME, '01:00:00')) " +
            "AND (:busType IS NULL OR bd.bus_type = :busType) " +
            "AND (:seatType IS NULL OR bd.seat_type = :seatType) " +
            "AND ((" +
            "    :filterBy = 'DEPARTURE' AND (:customStartTime IS NULL OR :customEndTime IS NULL OR " +
            "    (bs.departure_time BETWEEN :customStartTime AND :customEndTime)) " +
            ") OR (" +
            "    :filterBy = 'ARRIVAL' AND (:customStartTime IS NULL OR :customEndTime IS NULL OR " +
            "    (bs.arrival_time BETWEEN :customStartTime AND :customEndTime)) " +
            ")) " +
            "GROUP BY bs.id, bs.bus_id, bs.departure_time, bs.arrival_time, bs.total_traveling_hours, " +
            "bs.route_id, td.travel_date " +
            "ORDER BY bs.departure_time ASC",
            nativeQuery = true)
    List<BusSearchBean> findBuses(@Param("sourceCityId") Integer sourceCityId,
                                  @Param("destinationCityId") Integer destinationCityId,
                                  @Param("travelDate") LocalDate travelDate,
                                  @Param("busType") String busType,
                                  @Param("seatType") String seatType,
                                  @Param("customStartTime") String customStartTime,
                                  @Param("customEndTime") String customEndTime,
                                  @Param("filterBy") String filterBy);

}





