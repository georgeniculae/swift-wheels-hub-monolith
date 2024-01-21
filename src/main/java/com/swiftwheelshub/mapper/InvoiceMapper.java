package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.InvoiceDto;
import com.swiftwheelshub.entity.Invoice;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InvoiceMapper {

    InvoiceDto mapEntityToDto(Invoice invoice);

    Invoice mapDtoToEntity(InvoiceDto invoiceDto);

}
