package com.carrentalservice.service;

import com.carrentalservice.entity.Booking;
import com.carrentalservice.repository.BookingRepository;
import com.carrentalservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CarService carService;

    @Mock
    private CustomerService customerService;

    @Test
    void saveBookingUpdatedWithCustomerAndCarTest_success() {
        Booking booking = TestData.createBooking();

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(carService.findCarById(anyLong())).thenReturn(TestData.createCar());
        when(customerService.getCustomerLoggedIn()).thenReturn(TestData.createCustomer());

        assertDoesNotThrow(() -> bookingService.saveBookingUpdatedWithCustomerAndCar(booking));
        Booking actualBooking = bookingService.saveBookingUpdatedWithCustomerAndCar(booking);

        assertNotNull(actualBooking);
    }

    @Test
    void savedBookingWithUpdatedCarTest_success() {
        Booking booking = TestData.createBooking();

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(carService.findCarById(anyLong())).thenReturn(TestData.createCar());

        assertDoesNotThrow(() -> bookingService.saveBookingWithUpdatedCar(booking));
        Booking actualBooking = bookingService.saveBookingWithUpdatedCar(booking);

        assertNotNull(actualBooking);
    }
}
