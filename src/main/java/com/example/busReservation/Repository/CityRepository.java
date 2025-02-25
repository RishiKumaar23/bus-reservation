package com.example.busReservation.Repository;

import com.example.busReservation.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Integer> {
    @Query(value= "select * from city where id= :city_id",nativeQuery = true )
    Optional<City> findByCityId(@Param("city_id")Integer cityId );
    @Query(value = "select * from city where id not in (:city_id) and city_name in (:city_name)",nativeQuery = true)
    List<City> findCity(@Param("city_id")List<Integer> cityId, @Param("city_name")List<String> cityName);
}
