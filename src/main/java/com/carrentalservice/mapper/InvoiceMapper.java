package com.carrentalservice.mapper;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.entity.Invoice;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InvoiceMapper {

    @Mapping(target = "customerId", expression = "java(invoice.getCustomer().getId())")
    @Mapping(target = "carId", expression = "java(invoice.getCar().getId())")
    @Mapping(target = "receptionistEmployeeId", expression = "java(invoice.getReceptionistEmployee().getId())")
    @Mapping(target = "bookingId", expression = "java(invoice.getBooking().getId())")
    InvoiceDto mapEntityToDto(Invoice invoice);

    Invoice mapDtoToEntity(InvoiceDto invoiceDto);

}
