package com.swiftwheelshub.repository;

import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
            From Booking booking
            where booking.dateOfBooking = :dateOfBooking""")
    Optional<Booking> findByDateOfBooking(@Param("dateOfBooking") Date dateOfBooking);

    List<Booking> findBookingsByCustomer(Customer customer);

    Long countByCustomer(Customer customer);

}
