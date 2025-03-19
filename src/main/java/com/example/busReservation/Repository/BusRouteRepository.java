package com.example.busReservation.Repository;

import com.example.busReservation.Entity.BusRoutes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRouteRepository  extends JpaRepository<BusRoutes,Integer> {
    @Query(value = "SELECT * FROM pickup_point WHERE route_variant_id = :routeVariantId ORDER BY stop_order", nativeQuery = true)
    List<BusRoutes> findByRouteVariantIdOrderByStopOrder(@Param("routeVariantId") Integer routeVariantId);
}
