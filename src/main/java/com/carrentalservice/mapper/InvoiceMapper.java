package com.carrentalservice.mapper;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.entity.Invoice;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InvoiceMapper {

    InvoiceDto mapEntityToDto(Invoice invoice);

    Invoice mapDtoToEntity(InvoiceDto invoiceDto);

}
