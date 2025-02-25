package com.example.busReservation.Repository;

import com.example.busReservation.Bean.UsersBean;
import com.example.busReservation.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {
    @Query(value = "select * from user_details where (:id is null or id!=:id) And (adharId=:adharId or email=:email or phone=:phone", nativeQuery = true)
    List<UsersBean> findUsersDetails(@Param("adharId") Long adharId, @Param("email") String email, @Param("phone") String phone);
    @Query(value = "select * from user_details where id=:id ",nativeQuery = true)
    Optional<UserDetails> findByUserId(@Param("id") Integer userId);
}
