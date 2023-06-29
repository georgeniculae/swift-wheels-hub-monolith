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

    public static void assertCustomer(Customer customer, CustomerDto customerDto) {
        assertEquals(customer.getFirstName(), customerDto.getFirstName());
        assertEquals(customer.getLastName(), customerDto.getLastName());
        assertEquals(customer.getEmail(), customerDto.getEmail());
    }

    public static void assertCar(Car car, CarDto carDto) {
        assertEquals(car.getMake(), carDto.getMake());
        assertEquals(car.getModel(), carDto.getModel());
        assertEquals(car.getBodyType(), carDto.getBodyType());
        assertEquals(car.getYearOfProduction(), carDto.getYearOfProduction());
        assertEquals(car.getColor(), carDto.getColor());
        assertEquals(car.getMileage(), carDto.getMileage());
        assertEquals(car.getCarStatus(), carDto.getCarStatus());
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

    public static void assertBranch(Branch branch, BranchDto branchDto) {
        assertEquals(branch.getName(), branchDto.getName());
        assertEquals(branch.getAddress(), branchDto.getAddress());
        assertRentalOffice(branch.getRentalOffice(), branchDto.getRentalOffice());
    }

    public static void assertRentalOffice(RentalOffice rentalOffice, RentalOfficeDto rentalOfficeDto) {
        assertEquals(rentalOffice.getName(), rentalOfficeDto.getName());
        assertEquals(rentalOffice.getContactAddress(), rentalOfficeDto.getContactAddress());
        assertEquals(rentalOffice.getLogoType(), rentalOfficeDto.getLogoType());
    }

    public static void assertEmployee(Employee employee, EmployeeDto employeeDto) {
        assertEquals(employee.getFirstName(), employeeDto.getFirstName());
        assertEquals(employee.getLastName(), employeeDto.getLastName());
        assertEquals(employee.getJobPosition(), employeeDto.getJobPosition());
        assertEquals(employee.getFirstName(), employeeDto.getFirstName());
    }

    public static void assertInvoice(Invoice invoice, InvoiceDto invoiceDto) {
        assertCustomer(invoice.getCustomer(), invoiceDto.getCustomer());
        assertCar(invoice.getCar(), invoiceDto.getCar());
        assertEmployee(invoice.getReceptionistEmployee(), invoiceDto.getReceptionistEmployee());
        assertBooking(invoice.getBooking(), invoiceDto.getBooking());
        assertEquals(invoice.getCarDateOfReturn(), invoiceDto.getCarDateOfReturn());
        assertEquals(invoice.getIsVehicleDamaged(), invoiceDto.getIsVehicleDamaged());
        assertEquals(invoice.getDamageCost(), invoiceDto.getDamageCost());
        assertEquals(invoice.getAdditionalPayment(), invoiceDto.getAdditionalPayment());
        assertEquals(invoice.getTotalAmount(), invoiceDto.getTotalAmount());
        assertEquals(invoice.getComments(), invoiceDto.getComments());
    }

    public static void assertRevenue(Revenue revenue, RevenueDto revenueDto) {
        assertEquals(revenue.getDateOfRevenue(), revenueDto.getDateOfRevenue());
        assertEquals(revenue.getAmountFromBooking(), revenueDto.getAmountFromBooking());
    }
}
