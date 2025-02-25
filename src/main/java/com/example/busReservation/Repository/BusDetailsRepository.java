package com.example.busReservation.Repository;

import com.example.busReservation.Entity.BusDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BusDetailsRepository extends JpaRepository<BusDetails, Integer> {
    @Query(value = "select * from bus_details where (:id is NULL or id !=:id) AND bus_reg_no= :busRegNo", nativeQuery = true)
    BusDetails findByBusRegNo(@Param("id") Integer busId,@Param("busRegNo") String busRegNo);
    @Query(value = "select * from bus_details where id=:id", nativeQuery = true)
    Optional<BusDetails> findByBusId (@Param("id")Integer id);

}

