package com.sda.carrentalservice.repository;

import com.sda.carrentalservice.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    @Query("From Revenue revenue where lower(revenue.dailyRevenue) like '%:revenue%' or lower(revenue.monthlyRevenue) like '%:revenue%' or lower(revenue.weeklyRevenue) like '%:revenue%' or lower(revenue.yearlyRevenue) like '%:revenue%'")
    Revenue findRevenueByDetails(@Param("revenue") Double revenue);
}
