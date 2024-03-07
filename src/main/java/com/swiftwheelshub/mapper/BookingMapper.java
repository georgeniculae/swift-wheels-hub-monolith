package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BookingDto;
import com.swiftwheelshub.dto.CarDetails;
import com.swiftwheelshub.dto.CustomerDetails;
import com.swiftwheelshub.entity.Booking;
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
    BookingDto mapEntityToDto(Booking booking);

    Booking mapDtoToEntity(BookingDto bookingDto);

    CustomerDetails mapToCustomerDetails(Customer customer);

    CarDetails mapToCarDetails(Car car);

}
