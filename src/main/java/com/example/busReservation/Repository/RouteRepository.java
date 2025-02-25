package com.example.busReservation.Repository;

import com.example.busReservation.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    @Query(value = "select * from route where id=:id", nativeQuery = true)
    Optional <Route> findByRouteId(@Param("id") Integer routeId);
}
