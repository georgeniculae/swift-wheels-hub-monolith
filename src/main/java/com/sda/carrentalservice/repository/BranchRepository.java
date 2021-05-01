package com.sda.carrentalservice.repository;

import com.sda.carrentalservice.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query("From Branch branch where lower(branch.name) like '%:branchName%' or lower(branch.rentalOffice) like '%:branchName%'")
    Branch findBranchByName(@Param("branchName") String branchName);
}
