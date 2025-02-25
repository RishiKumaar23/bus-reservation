package com.example.busReservation.Repository;

import com.example.busReservation.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StateRepository extends JpaRepository<State,Integer> {
    @Query(value = "select * from state where id=:state_id",nativeQuery = true)
    Optional<State> findByStateId(@Param("state_id") Integer state_id);
    @Query(value = "select * from state where id not in (:state_id) and state_name in (:state_name)", nativeQuery = true)
    List<State> findByState(@Param("state_id") List<Integer> stateId, @Param("state_name") List<String> stateName);
}
