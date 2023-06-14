package com.carrentalservice.mapper;

import com.carrentalservice.dto.RentalDto;
import com.carrentalservice.entity.Rental;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RentalMapper {

    RentalDto mapEntityToDto(Rental rental);

    Rental mapDtoToEntity(RentalDto rentalDto);

}
