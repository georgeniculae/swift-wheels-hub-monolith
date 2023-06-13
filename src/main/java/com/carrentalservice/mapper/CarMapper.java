package com.carrentalservice.mapper;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {

    Car mapDtoToEntity(CarDto carDto);

    CarDto mapEntityToDto(Car car);

}
