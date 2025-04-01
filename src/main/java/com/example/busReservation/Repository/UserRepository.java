package com.example.busReservation.Repository;

import com.example.busReservation.Bean.UsersBean;
import com.example.busReservation.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {
    @Query(value = "SELECT * FROM passenger_details " +
            "WHERE (:id IS NULL OR id != :id) " +
            "AND (adhar_id = :adharId OR email = :email OR phone = :phone)",
            nativeQuery = true)
    List<UsersBean> findUsersDetails(@Param("id") Integer id,
                                     @Param("adharId") Long adharId,
                                     @Param("email") String email,
                                     @Param("phone") String phone);

//    @Query(value = "select * from passenger_details where id=:id ",nativeQuery = true)
//    Optional<UserDetails> findByUserId(@Param("id") Integer userId);
}
