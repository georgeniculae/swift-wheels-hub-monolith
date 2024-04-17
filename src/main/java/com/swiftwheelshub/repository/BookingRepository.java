package com.swiftwheelshub.repository;

import com.swiftwheelshub.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
            From Booking booking
            where booking.dateOfBooking = :dateOfBooking""")
    Optional<Booking> findByDateOfBooking(@Param("dateOfBooking") LocalDate dateOfBooking);

    @Query("""
            select b
            from Booking b
            where b.customerUsername = ?1""")
    List<Booking> findByCustomerUsername(String username);

    @Query("""
            select count(b)
            from Booking b
            where b.customerUsername = ?1""")
    long countByCustomerUsername(String username);

    @Transactional
    @Modifying
    @Query("""
            delete from Booking b
            where b.customerUsername = ?1""")
    void deleteByCustomerUsername(String customerUsername);

}
