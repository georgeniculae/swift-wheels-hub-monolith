package com.carrentalservice.util;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionUtils {

    public static void assertBooking(Booking booking, BookingDto bookingDto) {
        assertEquals(booking.getDateOfBooking(), bookingDto.getDateOfBooking());

    }

}
