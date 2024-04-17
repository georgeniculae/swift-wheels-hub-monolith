package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BranchDetails;
import com.swiftwheelshub.dto.CarRequest;
import com.swiftwheelshub.dto.CarResponse;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.Car;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CarMapper {

    @Mapping(target = "originalBranchDetails", expression = "java(mapToBranchDetails(car.getOriginalBranch()))")
    @Mapping(target = "actualBranchDetails", expression = "java(mapToBranchDetails(car.getActualBranch()))")
    CarResponse mapEntityToDto(Car car);

    Car mapDtoToEntity(CarRequest carRequest);

    BranchDetails mapToBranchDetails(Branch branch);

}
