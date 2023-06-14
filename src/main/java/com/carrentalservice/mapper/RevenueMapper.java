package com.carrentalservice.mapper;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.entity.Revenue;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RevenueMapper {

    RevenueDto mapEntityToDto(Revenue revenue);

    Revenue mapDtoToEntity(RevenueDto revenueDto);

}
