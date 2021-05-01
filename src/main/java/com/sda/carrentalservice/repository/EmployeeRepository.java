package com.sda.carrentalservice.repository;

import com.sda.carrentalservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("From Employee employee where lower(employee.firstName) like '%:employeeName%' or lower(employee.lastName) like '%:employeeName%'")
    Employee findEmployeeByName(@Param("employeeName") String employeeName);
}
