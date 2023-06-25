package com.carrentalservice.repository;

import com.carrentalservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByUsername(String username);

    @Query("""
            From Customer customer
            where lower(customer.firstName) like '%:customerName%'
            or lower(customer.lastName) like '%:customerName%'""")
    Customer findCustomerByName(@Param("customerName") String customerName);

    boolean existsByUsername(String username);

    @Query("From Customer customer " +
            "where customer.username not in (:adminUsername, :userUsername, :supportUsername, :customerUsername)")
    List<Customer> findCustomersWithoutBaseUsers(@Param("adminUsername") String adminUsername,
                                                 @Param("userUsername") String userUsername,
                                                 @Param("supportUsername") String supportUsername,
                                                 @Param("customerUsername") String customerUsername);

    @Query("Select count(customer) From Customer customer " +
            "where customer.username not in (:adminUsername, :userUsername, :supportUsername)")
    Long countCustomersWithoutBaseUsers(@Param("adminUsername") String adminUsername,
                                        @Param("userUsername") String userUsername,
                                        @Param("supportUsername") String supportUsername);

}
