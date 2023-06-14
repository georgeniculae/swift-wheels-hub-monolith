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

    CarDto mapEntityToDto(Car car);

    Car mapDtoToEntity(CarDto carDto);

}
