package com.carrentalservice.mapper;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.entity.Booking;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookingMapper {

    @Mapping(target = "customerId", expression = "java(booking.getCustomer().getId())")
    @Mapping(target = "carId", expression = "java(booking.getCar().getId())")
    @Mapping(target = "rentalBranchId", expression = "java(booking.getRentalBranch().getId())")
    @Mapping(target = "returnBranchId", expression = "java(booking.getReturnBranch().getId())")
    BookingDto mapEntityToDto(Booking booking);

    Booking mapDtoToEntity(BookingDto bookingDto);

}
