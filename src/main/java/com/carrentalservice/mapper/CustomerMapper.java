package com.carrentalservice.mapper;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

    Customer mapDtoToEntity(CustomerDto customerDto);

    CustomerDto mapEntityToDto(Customer customer);

}
