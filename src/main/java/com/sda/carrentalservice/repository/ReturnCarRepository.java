package com.sda.carrentalservice.repository;

import com.sda.carrentalservice.entity.ReturnCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnCarRepository extends JpaRepository<ReturnCar, Long> {

    @Query("From ReturnCar returnCar where lower(returnCar.additionalPayment) like '%:returnCarName%'")
    ReturnCar findReturnCarByName(@Param("returnCarName") String returnCarName);
}
