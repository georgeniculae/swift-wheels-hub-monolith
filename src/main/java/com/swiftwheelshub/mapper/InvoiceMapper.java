package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BookingDetails;
import com.swiftwheelshub.dto.CarDetails;
import com.swiftwheelshub.dto.CustomerDetails;
import com.swiftwheelshub.dto.EmployeeDetails;
import com.swiftwheelshub.dto.InvoiceDto;
import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.entity.Customer;
import com.swiftwheelshub.entity.Employee;
import com.swiftwheelshub.entity.Invoice;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InvoiceMapper {

    @Mapping(target = "customerDetails", expression = "java(mapToCustomerDetails(invoice.getCustomer()))")
    @Mapping(target = "carDetails", expression = "java(mapToCarDetails(invoice.getCar()))")
    @Mapping(target = "receptionistEmployeeDetails", expression = "java(mapToEmployeeDetails(invoice.getReceptionistEmployee()))")
    @Mapping(target = "bookingDetails", expression = "java(mapToBookingDetails(invoice.getBooking()))")
    InvoiceDto mapEntityToDto(Invoice invoice);

    Invoice mapDtoToEntity(InvoiceDto invoiceDto);

    CustomerDetails mapToCustomerDetails(Customer customer);

    CarDetails mapToCarDetails(Car car);

    EmployeeDetails mapToEmployeeDetails(Employee employee);

    BookingDetails mapToBookingDetails(Booking booking);

}
