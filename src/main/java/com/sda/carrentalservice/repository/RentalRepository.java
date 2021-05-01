package com.sda.carrentalservice.repository;

import com.sda.carrentalservice.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("From Rental rental where lower(rental.comments) like '%:rentalName%' or lower(rental.employeeOfRental.jobPosition) like '%:rentalName%'")
    Rental findRentalByName(@Param("rentalName") String rentalName);
}
