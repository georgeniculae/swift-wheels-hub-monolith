package com.carrentalservice.util;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionUtils {

    public static void assertBooking(Booking booking, BookingDto bookingDto) {
        assertEquals(booking.getDateOfBooking(), bookingDto.getDateOfBooking());
        assertEquals(booking.getDateFrom(), bookingDto.getDateFrom());
        assertEquals(booking.getDateTo(), bookingDto.getDateTo());
        assertEquals(booking.getAmount(), bookingDto.getAmount());
        assertCustomer(booking.getCustomer(), bookingDto.getCustomer());
    }

    private static void assertCustomer(Customer customer, CustomerDto customerDto) {
        assertEquals(customer.getFirstName(), customerDto.getFirstName());
        assertEquals(customer.getLastName(), customerDto.getLastName());
        assertEquals(customer.getEmail(), customerDto.getEmail());
        assertBookings(customer.getBookings(), customerDto.getBookings());
    }

    private static void assertBookings(List<Booking> bookings, List<BookingDto> bookingsDtoList) {
        assertEquals(bookings.size(), bookingsDtoList.size());

        for (int index = 0; index < bookings.size(); index++) {
            Booking booking = bookings.get(index);
            BookingDto bookingDto = bookingsDtoList.get(index);

            assertBooking(booking, bookingDto);
        }
    }

}
