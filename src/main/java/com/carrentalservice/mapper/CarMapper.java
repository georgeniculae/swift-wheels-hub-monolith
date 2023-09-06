package com.carrentalservice.mapper;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {

    @Mapping(target = "branchId", expression = "java(car.getBranch().getId())")
    CarDto mapEntityToDto(Car car);

    Car mapDtoToEntity(CarDto carDto);

}
