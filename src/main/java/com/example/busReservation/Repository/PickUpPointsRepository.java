package com.example.busReservation.Repository;

import com.example.busReservation.Entity.PickUpPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PickUpPointsRepository  extends JpaRepository<PickUpPoints,Integer> {
    @Query(value = "SELECT * FROM pickup_point WHERE route_variant_id = :routeVariantId ORDER BY stop_order", nativeQuery = true)
    List<PickUpPoints> findByRouteVariantIdOrderByStopOrder(@Param("routeVariantId") Integer routeVariantId);
}
