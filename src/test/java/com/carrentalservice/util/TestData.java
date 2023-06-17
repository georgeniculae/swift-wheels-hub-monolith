package com.carrentalservice.util;

import com.carrentalservice.dto.*;
import com.carrentalservice.entity.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

public class TestData {

    public static Booking createBooking() {
        Booking booking = new Booking();

        booking.setId(1L);
        booking.setDateOfBooking(Date.valueOf(LocalDate.of(2023, Month.APRIL, 20)));
        booking.setCar(createCar());
        booking.setCustomer(createCustomer());
        booking.setDateFrom(Date.valueOf(LocalDate.of(2023, Month.APRIL, 21)));
        booking.setDateTo(Date.valueOf(LocalDate.of(2023, Month.APRIL, 22)));
        booking.setReturnCar(createReturnCar());
        booking.setAmount(50D);
        booking.setRentalBranch(createRentalBranch());
        booking.setReturnBranch(createReturnBranch());

        return booking;
    }

    public static Car createCar() {
        Car car = new Car();

        car.setId(1L);
        car.setAmount(100D);
        car.setBranch(createRentalBranch());

        return car;
    }

    public static Customer createCustomer() {
        Customer customer = new Customer();

        customer.setId(1L);

        return customer;
    }

    public static Employee createEmployee() {
        Employee employee = new Employee();

        employee.setId(1L);
        employee.setWorkingBranch(createRentalBranch());

        return employee;
    }

    private static ReturnCar createReturnCar() {
        ReturnCar returnCar = new ReturnCar();

        returnCar.setId(1L);

        return returnCar;
    }

    public static Branch createRentalBranch() {
        Branch branch = new Branch();

        branch.setId(1L);
        branch.setRentalOffice(createRentalOffice());

        return branch;
    }

    public static Branch createReturnBranch() {
        Branch branch = new Branch();

        branch.setId(1L);
        branch.setRentalOffice(createRentalOffice());

        return branch;
    }

    public static RentalOffice createRentalOffice() {
        RentalOffice rentalOffice = new RentalOffice();

        rentalOffice.setId(1L);

        return rentalOffice;
    }

    public static BookingDto createBookingDto() {
        BookingDto bookingDto = new BookingDto();

        bookingDto.setId(1L);
        bookingDto.setDateOfBooking(Date.valueOf(LocalDate.of(2023, Month.APRIL, 20)));
        bookingDto.setCar(createCarDto());
        bookingDto.setCustomer(createCustomerDto());
        bookingDto.setDateFrom(Date.valueOf(LocalDate.of(2023, Month.APRIL, 21)));
        bookingDto.setDateTo(Date.valueOf(LocalDate.of(2023, Month.APRIL, 22)));
        bookingDto.setReturnCar(createReturnCarDto());
        bookingDto.setAmount(50D);
        bookingDto.setRentalBranch(createRentalBranchDto());
        bookingDto.setReturnBranch(createReturnBranchDto());

        return bookingDto;
    }

    public static CarDto createCarDto() {
        CarDto carDto = new CarDto();

        carDto.setId(1L);
        carDto.setAmount(100D);
        carDto.setBranch(createRentalBranchDto());

        return carDto;
    }

    public static CustomerDto createCustomerDto() {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(1L);

        return customerDto;
    }

    public static EmployeeDto createEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(1L);
        employeeDto.setWorkingBranch(createRentalBranchDto());

        return employeeDto;
    }

    private static ReturnCarDto createReturnCarDto() {
        ReturnCarDto returnCarDto = new ReturnCarDto();

        returnCarDto.setId(1L);

        return returnCarDto;
    }

    public static BranchDto createRentalBranchDto() {
        BranchDto branchDto = new BranchDto();

        branchDto.setId(1L);
        branchDto.setRentalOffice(createRentalOfficeDto());

        return branchDto;
    }

    public static BranchDto createReturnBranchDto() {
        BranchDto branchDto = new BranchDto();

        branchDto.setId(1L);
        branchDto.setRentalOffice(createRentalOfficeDto());

        return branchDto;
    }

    public static RentalOfficeDto createRentalOfficeDto() {
        RentalOfficeDto rentalOfficeDto = new RentalOfficeDto();

        rentalOfficeDto.setId(1L);

        return rentalOfficeDto;
    }

}
