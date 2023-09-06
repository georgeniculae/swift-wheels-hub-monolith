package com.carrentalservice.util;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.dto.CarDto;
import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.dto.UserDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.Car;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.entity.Invoice;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionUtils {

    public static void assertBooking(Booking booking, BookingDto bookingDto) {
        assertEquals(booking.getDateOfBooking(), bookingDto.getDateOfBooking());
        assertEquals(booking.getDateFrom(), bookingDto.getDateFrom());
        assertEquals(booking.getDateTo(), bookingDto.getDateTo());
        assertEquals(booking.getAmount(), bookingDto.getAmount());
        assertEquals(booking.getCustomer().getId(), bookingDto.getCustomerId());
        assertEquals(booking.getCar().getId(), bookingDto.getCarId());
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
        assertEquals(car.getUrlOfImage(), carDto.getUrlOfImage());
    }

    public static void assertBranch(Branch branch, BranchDto branchDto) {
        assertEquals(branch.getName(), branchDto.getName());
        assertEquals(branch.getAddress(), branchDto.getAddress());
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
