package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.CarDto;
import com.swiftwheelshub.entity.Car;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {

    CarDto mapEntityToDto(Car car);

    Car mapDtoToEntity(CarDto carDto);

}
