package com.swiftwheelshub.util;

import com.swiftwheelshub.dto.BookingDto;
import com.swiftwheelshub.dto.BranchDto;
import com.swiftwheelshub.dto.CarDto;
import com.swiftwheelshub.dto.CustomerDto;
import com.swiftwheelshub.dto.EmployeeDto;
import com.swiftwheelshub.dto.InvoiceDto;
import com.swiftwheelshub.dto.RentalOfficeDto;
import com.swiftwheelshub.dto.RevenueDto;
import com.swiftwheelshub.dto.UserDto;
import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.entity.Customer;
import com.swiftwheelshub.entity.Employee;
import com.swiftwheelshub.entity.Invoice;
import com.swiftwheelshub.entity.RentalOffice;
import com.swiftwheelshub.entity.Revenue;
import com.swiftwheelshub.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionUtils {

    public static void assertBooking(Booking booking, BookingDto bookingDto) {
        assertEquals(booking.getDateOfBooking(), bookingDto.getDateOfBooking());
        assertEquals(booking.getDateFrom(), bookingDto.getDateFrom());
        assertEquals(booking.getDateTo(), bookingDto.getDateTo());
        assertEquals(booking.getAmount(), bookingDto.getAmount());
        assertEquals(booking.getCustomer().getFirstName(), bookingDto.getCustomerDetails().getFirstName());
        assertEquals(booking.getCustomer().getLastName(), bookingDto.getCustomerDetails().getLastName());
        assertEquals(booking.getCustomer().getEmail(), bookingDto.getCustomerDetails().getEmail());
        assertEquals(booking.getCustomer().getAddress(), bookingDto.getCustomerDetails().getAddress());
        assertEquals(booking.getCar().getId(), bookingDto.getCarDetails().getId());
        assertEquals(booking.getRentalBranch().getId(), bookingDto.getRentalBranchId());
        assertEquals(booking.getReturnBranch().getId(), bookingDto.getReturnBranchId());
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
        assertEquals(car.getBranch().getId(), carDto.getBranchDetails().getId());
        assertEquals(car.getUrlOfImage(), carDto.getUrlOfImage());
    }

    public static void assertBranch(Branch branch, BranchDto branchDto) {
        assertEquals(branch.getName(), branchDto.getName());
        assertEquals(branch.getAddress(), branchDto.getAddress());
        assertEquals(branch.getRentalOffice().getId(), branchDto.getRentalOfficeDetails().getId());
    }

    public static void assertRentalOffice(RentalOffice rentalOffice, RentalOfficeDto rentalOfficeDto) {
        assertEquals(rentalOffice.getName(), rentalOfficeDto.getName());
        assertEquals(rentalOffice.getContactAddress(), rentalOfficeDto.getContactAddress());
        assertEquals(rentalOffice.getPhoneNumber(), rentalOfficeDto.getPhoneNumber());
    }

    public static void assertEmployee(Employee employee, EmployeeDto employeeDto) {
        assertEquals(employee.getFirstName(), employeeDto.getFirstName());
        assertEquals(employee.getLastName(), employeeDto.getLastName());
        assertEquals(employee.getJobPosition(), employeeDto.getJobPosition());
        assertEquals(employee.getFirstName(), employeeDto.getFirstName());
    }

    public static void assertInvoice(Invoice invoice, InvoiceDto invoiceDto) {
        assertEquals(invoice.getCustomer().getFirstName(), invoiceDto.getCustomerDetails().getFirstName());
        assertEquals(invoice.getCustomer().getLastName(), invoiceDto.getCustomerDetails().getLastName());
        assertEquals(invoice.getCustomer().getEmail(), invoiceDto.getCustomerDetails().getEmail());
        assertEquals(invoice.getCustomer().getAddress(), invoiceDto.getCustomerDetails().getAddress());
        assertEquals(invoice.getCar().getId(), invoiceDto.getCarDetails().getId());
        assertEquals(invoice.getReceptionistEmployee().getId(), invoiceDto.getReceptionistEmployeeDetails().getId());
        assertEquals(invoice.getBooking().getId(), invoiceDto.getBookingDetails().getId());
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

    public static void assertUser(User user, UserDto userDto) {
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getRole(), userDto.getRole());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
    }

}
