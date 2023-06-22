package com.carrentalservice.mapper;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.RentalOffice;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RentalOfficeMapper {

    RentalOfficeDto mapEntityToDto(RentalOffice rentalOffice);

    RentalOffice mapDtoToEntity(RentalOfficeDto rentalOfficeDto);

}
