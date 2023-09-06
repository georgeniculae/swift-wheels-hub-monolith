package com.carrentalservice.repository;

import com.carrentalservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
            From Employee employee
            where lower(employee.firstName) like '%:filter%'
            or lower(employee.lastName) like '%:filter%'""")
    Optional<Employee> findByFilter(@Param("filter") String filter);

    @Query("""
            From Employee employee
            where employee.workingBranch.id = :id""")
    List<Employee> findByBranchId(@Param("id") Long id);

}
