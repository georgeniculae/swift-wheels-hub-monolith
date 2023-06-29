package com.carrentalservice.service;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.Car;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.BookingMapper;
import com.carrentalservice.mapper.BookingMapperImpl;
import com.carrentalservice.repository.BookingRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestData;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        BookingDto bookingDto = TestUtils.getResourceAsJson("/data/BookingDto.json", BookingDto.class);
        Car car = booking.getCar();

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(carService.findEntityById(anyLong())).thenReturn(car);
        when(customerService.getLoggedInCustomer()).thenReturn(TestData.createCustomer());
        when(branchService.findEntityById(anyLong())).thenReturn(car.getBranch());

        assertDoesNotThrow(() -> bookingService.saveBooking(bookingDto));
        BookingDto actualBookingDto = bookingService.saveBooking(bookingDto);

        assertNotNull(actualBookingDto);

        verify(bookingMapper, times(2)).mapEntityToDto(any(Booking.class));
    }

    @Test
    void findBookingByIdTest_success() {
        Booking booking = TestData.createBooking();

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        assertDoesNotThrow(() -> bookingService.findBookingById(1L));
        BookingDto actualBookingDto = bookingService.findBookingById(1L);

        assertNotNull(actualBookingDto);
    }

    @Test
    void findBookingByIdTest_errorOnFindingById() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> bookingService.findBookingById(1L));

        assertNotNull(notFoundException);
        assertThat(notFoundException.getMessage()).contains("Booking with id 1 does not exist");
    }

    @Test
    void updateBookingTest_success() {
        Booking booking = TestUtils.getResourceAsJson("/data/Booking.json", Booking.class);
        BookingDto bookingDto = TestUtils.getResourceAsJson("/data/BookingDto.json", BookingDto.class);
        Car car = booking.getCar();

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(carService.findEntityById(anyLong())).thenReturn(car);

        assertDoesNotThrow(() -> bookingService.updateBooking(bookingDto));
        BookingDto updatedBookingDto = bookingService.updateBooking(bookingDto);

        assertNotNull(updatedBookingDto);
    }

    @Test
    void calculateAllAmountSpentByUserTest_success() {
        Booking booking = TestData.createBooking();
        Customer customer = TestData.createCustomer();

        when(customerService.getLoggedInCustomer()).thenReturn(customer);
        when(bookingRepository.findBookingsByCustomer(any(Customer.class))).thenReturn(List.of(booking));

        Double amount = bookingService.getAmountSpentByLoggedInUser();
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

        when(bookingRepository.findByDateOfBooking(Date.valueOf(LocalDate.of(2050, Month.FEBRUARY, 20))))
                .thenReturn(Optional.of(booking));

        assertDoesNotThrow(() -> bookingService.findBookingByDateOfBooking("2050-02-20"));
        BookingDto bookingDto = bookingService.findBookingByDateOfBooking("2050-02-20");

        AssertionUtils.assertBooking(booking, bookingDto);
    }

    @Test
    void findBookingByDateOfBookingTest_errorOnFindingByDateOfBooking() {
        when(bookingRepository.findByDateOfBooking(Date.valueOf(LocalDate.of(2050, Month.FEBRUARY, 20))))
                .thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> bookingService.findBookingByDateOfBooking("2050-02-20"));

        assertNotNull(notFoundException);
        assertEquals("Booking from date: 2050-02-20 does not exist", notFoundException.getMessage());
    }

}
