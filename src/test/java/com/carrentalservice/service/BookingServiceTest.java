package com.carrentalservice.service;

import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.BookingRepository;
import com.carrentalservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Test
    void findBookingByIdTest_success() {
        Booking booking = TestData.createBooking();

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        assertDoesNotThrow(() -> bookingService.findBookingById(1L));
        Booking actualBooking = bookingService.findBookingById(1L);

        assertNotNull(actualBooking);
    }

    @Test
    void findBookingByIdTest_errorOnFindingById() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> bookingService.findBookingById(1L));

        assertNotNull(notFoundException);
        assertThat(notFoundException.getMessage()).contains("Booking with id 1 does not exist");
    }

    @Test
    void deleteBookingByIdTest_success() {
        Booking booking = TestData.createBooking();

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        assertDoesNotThrow(() -> bookingService.deleteBookingById(1L));
    }

    @Test
    void updateBookingTest_success() {
        Booking booking = TestData.createBooking();

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        assertDoesNotThrow(() -> bookingService.updateBooking(booking));
        Booking updatedBooking = bookingService.updateBooking(booking);

        assertNotNull(updatedBooking);
    }

    @Test
    void calculateAllAmountSpentByUserTest_success() {
        Booking booking = TestData.createBooking();
        Customer customer = TestData.createCustomer();

        when(bookingRepository.findBookingByCustomer(any(Customer.class))).thenReturn(List.of(booking));
        Double amount = bookingService.getAmountSpentByUser(customer);
        assertEquals(50, amount);
    }

    @Test
    void getSumOfAllBookingAmountTest_success() {
        Booking booking = TestData.createBooking();

        when(bookingRepository.findAll()).thenReturn(List.of(booking));

        assertDoesNotThrow(() -> bookingService.getSumOfAllBookingAmount());
        Double sumOfAllBookingAmount = bookingService.getSumOfAllBookingAmount();

        assertEquals(50, sumOfAllBookingAmount);
    }

}
