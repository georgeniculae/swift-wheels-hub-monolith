package com.swiftwheelshub.repository;

import com.swiftwheelshub.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    @Query("""
            From Revenue revenue where
            revenue.dateOfRevenue = :dateOfRevenue""")
    Optional<Revenue> findByDateOfRevenue(@Param("dateOfRevenue") LocalDate dateOfRevenue);

    @Query("SELECT sum(revenue.amountFromBooking) from Revenue revenue")
    BigDecimal getTotalAmount();

}
