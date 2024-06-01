package com.swiftwheelshub.util;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.dto.BookingResponse;
import com.swiftwheelshub.dto.BranchRequest;
import com.swiftwheelshub.dto.BranchResponse;
import com.swiftwheelshub.dto.CarRequest;
import com.swiftwheelshub.dto.CarResponse;
import com.swiftwheelshub.dto.EmployeeRequest;
import com.swiftwheelshub.dto.EmployeeResponse;
import com.swiftwheelshub.dto.InvoiceRequest;
import com.swiftwheelshub.dto.InvoiceResponse;
import com.swiftwheelshub.dto.RentalOfficeRequest;
import com.swiftwheelshub.dto.RentalOfficeResponse;
import com.swiftwheelshub.dto.RevenueResponse;
import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.entity.Employee;
import com.swiftwheelshub.entity.Invoice;
import com.swiftwheelshub.entity.RentalOffice;
import com.swiftwheelshub.entity.Revenue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionUtils {

    public static void assertBookingRequest(Booking booking, BookingRequest bookingRequest) {
        assertEquals(booking.getDateOfBooking(), bookingRequest.getDateOfBooking());
        assertEquals(booking.getDateFrom(), bookingRequest.getDateFrom());
        assertEquals(booking.getDateTo(), bookingRequest.getDateTo());
        assertEquals(booking.getAmount(), bookingRequest.getAmount());
        assertEquals(booking.getCustomerUsername(), bookingRequest.getCustomerUsername());
    }

    public static void assertBookingResponse(Booking booking, BookingResponse bookingResponse) {
        assertEquals(booking.getDateOfBooking(), bookingResponse.getDateOfBooking());
        assertEquals(booking.getDateFrom(), bookingResponse.getDateFrom());
        assertEquals(booking.getDateTo(), bookingResponse.getDateTo());
        assertEquals(booking.getAmount(), bookingResponse.getAmount());
        assertEquals(booking.getCustomerUsername(), bookingResponse.getCustomerUsername());
        assertEquals(booking.getCar().getId(), bookingResponse.getCarDetails().getId());
        assertEquals(booking.getRentalBranch().getId(), bookingResponse.getRentalBranchId());
        assertEquals(booking.getReturnBranch().getId(), bookingResponse.getReturnBranchId());
    }

//    public static void assertCustomerRequest(Customer customer, CustomerRequest customerRequest) {
//        assertEquals(customer.getFirstName(), customerRequest.getFirstName());
//        assertEquals(customer.getLastName(), customerRequest.getLastName());
//        assertEquals(customer.getEmail(), customerRequest.getEmail());
//    }
//
//    public static void assertCustomerResponse(Customer customer, CustomerResponse customerResponse) {
//        assertEquals(customer.getFirstName(), customerResponse.getFirstName());
//        assertEquals(customer.getLastName(), customerResponse.getLastName());
//        assertEquals(customer.getEmail(), customerResponse.getEmail());
//    }

    public static void assertCarRequest(Car car, CarRequest carRequest) {
        assertEquals(car.getMake(), carRequest.getMake());
        assertEquals(car.getModel(), carRequest.getModel());
        assertEquals(car.getBodyType(), carRequest.getBodyType());
        assertEquals(car.getYearOfProduction(), carRequest.getYearOfProduction());
        assertEquals(car.getColor(), carRequest.getColor());
        assertEquals(car.getMileage(), carRequest.getMileage());
        assertEquals(car.getCarStatus(), carRequest.getCarStatus());
        assertEquals(car.getAmount(), carRequest.getAmount());
    }

    public static void assertCarResponse(Car car, CarResponse carResponse) {
        assertEquals(car.getMake(), carResponse.getMake());
        assertEquals(car.getModel(), carResponse.getModel());
        assertEquals(car.getBodyType(), carResponse.getBodyType());
        assertEquals(car.getYearOfProduction(), carResponse.getYearOfProduction());
        assertEquals(car.getColor(), carResponse.getColor());
        assertEquals(car.getMileage(), carResponse.getMileage());
        assertEquals(car.getCarStatus(), carResponse.getCarStatus());
        assertEquals(car.getAmount(), carResponse.getAmount());
        assertEquals(car.getOriginalBranch().getId(), carResponse.getOriginalBranchDetails().getId());
        assertEquals(car.getActualBranch().getId(), carResponse.getActualBranchDetails().getId());
    }

    public static void assertBranchRequest(Branch branch, BranchRequest branchRequest) {
        assertEquals(branch.getName(), branchRequest.getName());
        assertEquals(branch.getAddress(), branchRequest.getAddress());
    }

    public static void assertBranchResponse(Branch branch, BranchResponse branchResponse) {
        assertEquals(branch.getName(), branchResponse.getName());
        assertEquals(branch.getAddress(), branchResponse.getAddress());
        assertEquals(branch.getRentalOffice().getId(), branchResponse.getRentalOfficeDetails().getId());
    }

    public static void assertRentalOfficeRequest(RentalOffice rentalOffice, RentalOfficeRequest rentalOfficeRequest) {
        assertEquals(rentalOffice.getName(), rentalOfficeRequest.getName());
        assertEquals(rentalOffice.getContactAddress(), rentalOfficeRequest.getContactAddress());
        assertEquals(rentalOffice.getPhoneNumber(), rentalOfficeRequest.getPhoneNumber());
    }

    public static void assertRentalOfficeResponse(RentalOffice rentalOffice, RentalOfficeResponse rentalOfficeResponse) {
        assertEquals(rentalOffice.getName(), rentalOfficeResponse.getName());
        assertEquals(rentalOffice.getContactAddress(), rentalOfficeResponse.getContactAddress());
        assertEquals(rentalOffice.getPhoneNumber(), rentalOfficeResponse.getPhoneNumber());
    }

    public static void assertEmployeeRequest(Employee employee, EmployeeRequest employeeRequest) {
        assertEquals(employee.getFirstName(), employeeRequest.getFirstName());
        assertEquals(employee.getLastName(), employeeRequest.getLastName());
        assertEquals(employee.getJobPosition(), employeeRequest.getJobPosition());
        assertEquals(employee.getFirstName(), employeeRequest.getFirstName());
    }

    public static void assertEmployeeResponse(Employee employee, EmployeeResponse employeeResponse) {
        assertEquals(employee.getFirstName(), employeeResponse.getFirstName());
        assertEquals(employee.getLastName(), employeeResponse.getLastName());
        assertEquals(employee.getJobPosition(), employeeResponse.getJobPosition());
        assertEquals(employee.getFirstName(), employeeResponse.getFirstName());
    }

    public static void assertInvoiceRequest(Invoice invoice, InvoiceRequest invoiceRequest) {
        assertEquals(invoice.getCarDateOfReturn(), invoiceRequest.getCarDateOfReturn());
        assertEquals(invoice.getIsVehicleDamaged(), invoiceRequest.getIsVehicleDamaged());
        assertEquals(invoice.getDamageCost(), invoiceRequest.getDamageCost());
        assertEquals(invoice.getAdditionalPayment(), invoiceRequest.getAdditionalPayment());
        assertEquals(invoice.getTotalAmount(), invoiceRequest.getTotalAmount());
        assertEquals(invoice.getComments(), invoiceRequest.getComments());
    }

    public static void assertInvoiceResponse(Invoice invoice, InvoiceResponse invoiceResponse) {
        assertEquals(invoice.getCustomerUsername(), invoiceResponse.getCustomerUsername());
        assertEquals(invoice.getCar().getId(), invoiceResponse.getCarDetails().getId());
        assertEquals(invoice.getReceptionistEmployee().getId(), invoiceResponse.getReceptionistEmployeeDetails().getId());
        assertEquals(invoice.getBooking().getId(), invoiceResponse.getBookingDetails().getId());
        assertEquals(invoice.getCarDateOfReturn(), invoiceResponse.getCarDateOfReturn());
        assertEquals(invoice.getIsVehicleDamaged(), invoiceResponse.getIsVehicleDamaged());
        assertEquals(invoice.getDamageCost(), invoiceResponse.getDamageCost());
        assertEquals(invoice.getAdditionalPayment(), invoiceResponse.getAdditionalPayment());
        assertEquals(invoice.getTotalAmount(), invoiceResponse.getTotalAmount());
        assertEquals(invoice.getComments(), invoiceResponse.getComments());
    }

    public static void assertRevenueResponse(Revenue revenue, RevenueResponse revenueResponse) {
        assertEquals(revenue.getDateOfRevenue(), revenueResponse.getDateOfRevenue());
        assertEquals(revenue.getAmountFromBooking(), revenueResponse.getAmountFromBooking());
    }

}
