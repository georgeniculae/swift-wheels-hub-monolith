package com.carrentalservice.util;

import com.carrentalservice.dto.*;
import com.carrentalservice.entity.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionUtils {

    public static void assertBooking(Booking booking, BookingDto bookingDto) {
        assertEquals(booking.getDateOfBooking(), bookingDto.getDateOfBooking());
        assertEquals(booking.getDateFrom(), bookingDto.getDateFrom());
        assertEquals(booking.getDateTo(), bookingDto.getDateTo());
        assertEquals(booking.getAmount(), bookingDto.getAmount());
        assertCustomer(booking.getCustomer(), bookingDto.getCustomer());
        assertCar(booking.getCar(), bookingDto.getCar());
        assertRentalBranch(booking.getRentalBranch(), bookingDto.getRentalBranch());
        assertReturnBranch(booking.getReturnBranch(), bookingDto.getReturnBranch());
    }

    private static void assertCustomer(Customer customer, CustomerDto customerDto) {
        assertEquals(customer.getFirstName(), customerDto.getFirstName());
        assertEquals(customer.getLastName(), customerDto.getLastName());
        assertEquals(customer.getEmail(), customerDto.getEmail());
    }

    private static void assertCar(Car car, CarDto carDto) {
        assertEquals(car.getMake(), carDto.getMake());
        assertEquals(car.getModel(), carDto.getModel());
        assertEquals(car.getBodyType(), carDto.getBodyType());
        assertEquals(car.getYearOfProduction(), carDto.getYearOfProduction());
        assertEquals(car.getColor(), carDto.getColor());
        assertEquals(car.getMileage(), carDto.getMileage());
        assertEquals(car.getStatus(), carDto.getStatus());
        assertEquals(car.getAmount(), carDto.getAmount());
        assertBranch(car.getBranch(), carDto.getBranch());
        assertEquals(car.getUrlOfImage(), carDto.getUrlOfImage());
    }

    private static void assertRentalBranch(Branch rentalBranch, BranchDto rentalBranchDto) {
        assertEquals(rentalBranch.getName(), rentalBranchDto.getName());
        assertEquals(rentalBranch.getAddress(), rentalBranchDto.getAddress());
        assertRentalOffice(rentalBranch.getRentalOffice(), rentalBranchDto.getRentalOffice());
    }

    private static void assertReturnBranch(Branch returnBranch, BranchDto returnBranchDto) {
        assertEquals(returnBranch.getName(), returnBranchDto.getName());
        assertEquals(returnBranch.getAddress(), returnBranchDto.getAddress());
        assertRentalOffice(returnBranch.getRentalOffice(), returnBranchDto.getRentalOffice());
    }

    private static void assertBranch(Branch branch, BranchDto branchDto) {
        assertEquals(branch.getName(), branchDto.getName());
        assertEquals(branch.getAddress(), branchDto.getAddress());
        assertRentalOffice(branch.getRentalOffice(), branchDto.getRentalOffice());
    }

    private static void assertRentalOffice(RentalOffice rentalOffice, RentalOfficeDto rentalOfficeDto) {
        assertEquals(rentalOffice.getName(), rentalOfficeDto.getName());
        assertEquals(rentalOffice.getContactAddress(), rentalOfficeDto.getInternetDomain());
        assertEquals(rentalOffice.getContactAddress(), rentalOfficeDto.getContactAddress());
        assertEquals(rentalOffice.getOwner(), rentalOfficeDto.getOwner());
        assertEquals(rentalOffice.getLogoType(), rentalOfficeDto.getLogoType());
    }

    private static void assertEmployee(Employee employeeOfRental, EmployeeDto employeeOfRentalDto) {
        assertEquals(employeeOfRental.getFirstName(), employeeOfRentalDto.getFirstName());
        assertEquals(employeeOfRental.getLastName(), employeeOfRentalDto.getLastName());
        assertEquals(employeeOfRental.getJobPosition(), employeeOfRentalDto.getJobPosition());
        assertEquals(employeeOfRental.getFirstName(), employeeOfRentalDto.getFirstName());
    }

}
