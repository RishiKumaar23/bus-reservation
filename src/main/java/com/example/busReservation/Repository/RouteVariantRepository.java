package com.example.busReservation.Repository;

import com.example.busReservation.Entity.Route;
import com.example.busReservation.Entity.RouteVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RouteVariantRepository extends JpaRepository<RouteVariant, Integer> {
//    @Query(value = "select * from route_variant  where id=:id", nativeQuery = true)
//    Optional<RouteVariant> findById(@Param("id") Integer routeVariantId);
}
