package com.example.busReservation.Repository;

import com.example.busReservation.Bean.BusOwnerBean;
import com.example.busReservation.Entity.BusOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BusOwnersRepository extends JpaRepository<BusOwner,Integer> {

    @Query(value = "SELECT * FROM vehicle_owner WHERE (:id IS NULL OR id != :id) AND (company_name = :companyName OR email_id = :emailId OR contact_no = :contactNo)",nativeQuery = true)
    List<BusOwnerBean> findOwnerName(@Param("id") Integer ownerId,
                                 @Param("companyName") String companyName,
                                 @Param("emailId") String emailId,
                                 @Param("contactNo") String contactNo);

    @Query(value = "SELECT * FROM vehicle_owner WHERE id = :id", nativeQuery = true)
    Optional<BusOwner> findByOwnerId(@Param("id") Integer ownerID);
}


