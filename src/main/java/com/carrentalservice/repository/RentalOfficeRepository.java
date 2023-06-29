package com.carrentalservice.repository;

import com.carrentalservice.entity.RentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalOfficeRepository extends JpaRepository<RentalOffice, Long> {

    @Query("""
            From RentalOffice rentalOffice
            where lower(rentalOffice.name) like '%:rentalOfficeName%'""")
    Optional<RentalOffice> findRentalOfficeByName(@Param("rentalOfficeName") String rentalOfficeName);

}
