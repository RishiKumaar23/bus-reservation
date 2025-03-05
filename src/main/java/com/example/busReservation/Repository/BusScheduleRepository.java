package com.example.busReservation.Repository;

import com.example.busReservation.Bean.BusSearchBean;
import com.example.busReservation.Entity.BusSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BusScheduleRepository extends JpaRepository<BusSchedules, Integer> {
    //        @Query(value = "select bs.id as id, bs.bus_id as busId,bs.departure_time as departureTime," +
//            "bs.arrival_time as arrivalTime,bs.total_traveling_hours as travelHours,bs.route_id as routeId," +
//            "td.travel_date as travelDate " +
//            "from bus_schedules bs " +
//            "join route r on bs.route_id=r.id " +
//            "join bus_travel_dates td on td.schedule_id=bs.id  " +
//            "where r.source_city_id =:sourceCityId And r.destination_city_id=:destinationCityId " +
//                "and td.travel_date=:travelDate " +
//                "and bs.departure_time > CURRENT_TIME " +
//                "order by departure_time ASC",nativeQuery = true)
//    List<BusSearchBean> findBuses(@Param("sourceCityId") Integer sourceCityId, @Param("destinationCityId") Integer destinationCityId, @Param("travelDate") LocalDate travelDate);
//
//}
    @Query(value = "SELECT bs.id AS id, bs.bus_id AS busId, bs.departure_time AS departureTime, " +
            "bs.arrival_time AS arrivalTime, bs.total_traveling_hours AS travelHours, bs.route_id AS routeId, " +
            "td.travel_date AS travelDate " +
            "from bus_schedules bs " +
            "join route r ON bs.route_id = r.id " +
            "join bus_travel_dates td ON td.schedule_id = bs.id " +
            "where r.source_city_id = :sourceCityId " +
            "and r.destination_city_id = :destinationCityId " +
            "and td.travel_date = :travelD ate " +
            "and bs.departure_time > CURRENT_TIME " +
            "and (:busType IS NULL OR bs.bus_type = :busType) " +
            "and (:seatType IS NULL OR bs.seat_type = :seatType) " +
            "and (:departureTimeRange IS NULL OR " +
            "   (case " +
            "       when :departureTimeRange = 'Before 10 AM' THEN bs.departure_time < '10:00:00' " +
            "       when :departureTimeRange = '10 AM to 5 PM' THEN bs.departure_time BETWEEN '10:00:00' AND '17:00:00' " +
            "       when :departureTimeRange = '5 PM to 10 PM' THEN bs.departure_time BETWEEN '17:00:00' AND '22:00:00' " +
            "       when :departureTimeRange = 'After 10 PM' THEN bs.departure_time > '22:00:00' " +
            "   END)) " +
            "ORDER BY departure_time ASC", nativeQuery = true)
    List<BusSearchBean> findBuses(@Param("sourceCityId") Integer sourceCityId,
                                  @Param("destinationCityId") Integer destinationCityId,
                                  @Param("travelDate") LocalDate travelDate,
                                  @Param("busType") String busType,
                                  @Param("seatType") String seatType,
                                  @Param("departureTimeRange") String departureTimeRange);


}