package com.example.busReservation.Repository;

import com.example.busReservation.Entity.RouteVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RouteVariantRepository extends JpaRepository<RouteVariant,Integer> {
//    @Query(value = "select * from route_variant where (:id is null or id=:id ) and ")
}
