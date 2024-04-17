package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.dto.BookingResponse;
import com.swiftwheelshub.dto.BranchDetails;
import com.swiftwheelshub.dto.CarDetails;
import com.swiftwheelshub.entity.Booking;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import org.apache.commons.lang3.ObjectUtils;
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

    @Mapping(target = "carDetails", expression = "java(mapToCarDetails(booking.getCar()))")
    @Mapping(target = "rentalBranchId", expression = "java(booking.getRentalBranch().getId())")
    @Mapping(target = "returnBranchId", expression = "java(getReturnBranchId(booking))")
    BookingResponse mapEntityToDto(Booking booking);

    Booking mapDtoToEntity(BookingRequest bookingRequest);

    @Mapping(target = "originalBranchDetails", expression = "java(mapToBranchDetails(car.getOriginalBranch()))")
    @Mapping(target = "actualBranchDetails", expression = "java(mapToBranchDetails(car.getActualBranch()))")
    CarDetails mapToCarDetails(Car car);

    BranchDetails mapToBranchDetails(Branch branch);

    default Long getReturnBranchId(Booking booking) {
        return ObjectUtils.isEmpty(booking.getReturnBranch()) ? null : booking.getReturnBranch().getId();
    }

}
