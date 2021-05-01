package com.sda.carrentalservice.repository;

import com.sda.carrentalservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByUsername(String username);

    @Query("From Customer customer where lower(customer.firstName) like '%:customerName%' or lower(customer.lastName) like '%:customerName%'")
    Customer findCustomerByName(@Param("customerName") String customerName);
}
