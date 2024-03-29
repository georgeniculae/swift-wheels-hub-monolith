package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.dto.BookingResponse;
import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.entity.Customer;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.BookingMapper;
import com.swiftwheelshub.mapper.BookingMapperImpl;
import com.swiftwheelshub.repository.BookingRepository;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
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

    @Mock
    private BranchService branchService;

    @Spy
    private BookingMapper bookingMapper = new BookingMapperImpl();

    @Test
    void saveBookingUpdatedWithCustomerAndCarTest_success() {
        Booking booking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);
        BookingRequest bookingRequest = TestUtils.getResourceAsJson("/data/BookingRequest.json", BookingRequest.class);
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);
        Car car = booking.getCar();

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(carService.findEntityById(anyLong())).thenReturn(car);
        when(customerService.getLoggedInCustomer()).thenReturn(customer);
        when(branchService.findEntityById(anyLong())).thenReturn(car.getBranch());

        BookingResponse actualBookingResponse = bookingService.saveBooking(bookingRequest);

        assertNotNull(actualBookingResponse);

        verify(bookingMapper).mapEntityToDto(any(Booking.class));
    }

    @Test
    void findBookingByIdTest_success() {
        Booking booking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        BookingResponse actualBookingResponse = bookingService.findBookingById(1L);

        assertNotNull(actualBookingResponse);
    }

    @Test
    void findBookingByIdTest_errorOnFindingById() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException = assertThrows(SwiftWheelsHubNotFoundException.class, () -> bookingService.findBookingById(1L));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertThat(swiftWheelsHubNotFoundException.getMessage()).contains("Booking with id 1 does not exist");
    }

    @Test
    void updateBookingTest_success() {
        Booking booking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);
        BookingRequest bookingRequest = TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingRequest.class);
        Car car = booking.getCar();

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(carService.findEntityById(anyLong())).thenReturn(car);

        BookingResponse updatedBookingResponse = bookingService.updateBooking(1L, bookingRequest);

        assertNotNull(updatedBookingResponse);
    }

    @Test
    void calculateAllAmountSpentByUserTest_success() {
        Booking booking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);

        when(customerService.getLoggedInCustomer()).thenReturn(customer);
        when(bookingRepository.findBookingsByCustomer(any(Customer.class))).thenReturn(List.of(booking));

        BigDecimal amount = bookingService.getAmountSpentByLoggedInUser();
        assertEquals(BigDecimal.valueOf(500), amount);
    }

    @Test
    void getSumOfAllBookingAmountTest_success() {
        Booking booking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);

        when(bookingRepository.findAll()).thenReturn(List.of(booking));

        assertDoesNotThrow(() -> bookingService.getSumOfAllBookingAmount());
        BigDecimal sumOfAllBookingAmount = bookingService.getSumOfAllBookingAmount();

        assertEquals(BigDecimal.valueOf(500), sumOfAllBookingAmount);
    }

    @Test
    void countCustomersWithBookingsTest_success() {
        Booking booking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);

        when(bookingRepository.findAll()).thenReturn(List.of(booking));

        assertDoesNotThrow(() -> bookingService.countCustomersWithBookings());
        assertEquals(1, bookingService.countCustomersWithBookings());
    }

    @Test
    void findBookingByDateOfBookingTest_success() {
        Booking booking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);

        when(bookingRepository.findByDateOfBooking(LocalDate.of(2050, Month.FEBRUARY, 20)))
                .thenReturn(Optional.of(booking));

        BookingResponse bookingResponse = bookingService.findBookingByDateOfBooking("2050-02-20");

        AssertionUtils.assertBookingResponse(booking, bookingResponse);
    }

    @Test
    void findBookingByDateOfBookingTest_errorOnFindingByDateOfBooking() {
        when(bookingRepository.findByDateOfBooking(LocalDate.of(2050, Month.FEBRUARY, 20)))
                .thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> bookingService.findBookingByDateOfBooking("2050-02-20"));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Booking from date: 2050-02-20 does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

}
