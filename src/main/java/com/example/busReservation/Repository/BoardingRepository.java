package com.example.busReservation.Repository;

import com.example.busReservation.Entity.BoardingPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardingRepository extends JpaRepository<BoardingPoints,Integer> {
@Query(value = "select * from boarding_point where :id is null or id=:id",nativeQuery = true)
    Optional<Integer> findByBoardingId(@Param("id")Integer cityId );
    @Query(value = "SELECT * FROM boarding_points bp WHERE bp.boarding_name = :boardingName AND bp.city_id = :cityId", nativeQuery = true)
    BoardingPoints findByBoardingNameAndCity(@Param("boardingName") String boardingName, @Param("cityId") Integer cityId);

}
