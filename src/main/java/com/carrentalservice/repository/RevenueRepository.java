package com.carrentalservice.repository;

import com.carrentalservice.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    @Query("""
            From Revenue revenue where
            revenue.dailyRevenue = :revenue or
            revenue.monthlyRevenue = :revenue or
            revenue.weeklyRevenue = :revenue or
            revenue.yearlyRevenue = :revenue""")
    Revenue findRevenueByDetails(@Param("revenue") Double revenue);

}
