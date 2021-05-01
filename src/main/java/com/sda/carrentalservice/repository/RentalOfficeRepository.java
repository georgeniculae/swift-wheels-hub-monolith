package com.sda.carrentalservice.repository;

import com.sda.carrentalservice.entity.RentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOfficeRepository extends JpaRepository<RentalOffice, Long> {

    @Query("From RentalOffice rentalOffice where lower(rentalOffice.name) like '%:rentalOfficeName%' or lower(rentalOffice.owner) like '%:rentalOfficeName%'")
    RentalOffice findRentalOfficeByName(@Param("rentalOfficeName") String rentalOfficeName);
}
