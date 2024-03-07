package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.dto.BookingResponse;
import com.swiftwheelshub.dto.BranchDetails;
import com.swiftwheelshub.dto.CarDetails;
import com.swiftwheelshub.dto.CustomerDetails;
import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.entity.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BookingMapper {

    @Mapping(target = "customerDetails", expression = "java(mapToCustomerDetails(booking.getCustomer()))")
    @Mapping(target = "carDetails", expression = "java(mapToCarDetails(booking.getCar()))")
    @Mapping(target = "rentalBranchId", expression = "java(booking.getRentalBranch().getId())")
    @Mapping(target = "returnBranchId", expression = "java(booking.getReturnBranch().getId())")
    BookingResponse mapEntityToDto(Booking booking);

    Booking mapDtoToEntity(BookingRequest bookingRequest);

    CustomerDetails mapToCustomerDetails(Customer customer);

    @Mapping(target = "branchDetails", expression = "java(mapToBranchDetails(car.getBranch()))")
    CarDetails mapToCarDetails(Car car);

    BranchDetails mapToBranchDetails(Branch branch);

}
