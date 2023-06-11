package com.carrentalservice.repository;

import com.carrentalservice.entity.ReturnCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnCarRepository extends JpaRepository<ReturnCar, Long> {

    @Query("From ReturnCar returnCar where returnCar.comments like '%:comments%'")
    ReturnCar findReturnCarByName(@Param("comments") String comments);

}
