package com.example.busReservation.Repository;

import com.example.busReservation.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    @Query(value = "SELECT * FROM country  WHERE id = :country_id", nativeQuery = true)
    Optional<Country> findByCountryId(@Param("country_id") Integer countryId);
    @Query(value = "select * from country where id!=:country_id and country_name = :country_name", nativeQuery = true)
    Country findByCountry(@Param("country_id")Integer countryId, @Param("country_name") String countryName);
}
