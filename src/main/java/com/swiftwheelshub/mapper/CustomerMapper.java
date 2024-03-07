package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.CustomerDto;
import com.swiftwheelshub.entity.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CustomerMapper {

    CustomerDto mapEntityToDto(Customer customer);

    Customer mapDtoToEntity(CustomerDto customerDto);

}
