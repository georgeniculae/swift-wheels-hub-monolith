package com.carrentalservice.mapper;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.entity.Invoice;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class InvoiceMapperTest {

    private final InvoiceMapper invoiceMapper = new InvoiceMapperImpl();

    @Test
    void mapEntityToDtoTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);

        InvoiceDto invoiceDto = invoiceMapper.mapEntityToDto(invoice);

        assertNotNull(invoiceDto);
        AssertionUtils.assertInvoice(invoice, invoiceDto);
    }

    @Test
    void mapDtoToEntityTest_success() {
        InvoiceDto invoiceDto = TestUtils.getResourceAsJson("/data/InvoiceDto.json", InvoiceDto.class);

        Invoice invoice = invoiceMapper.mapDtoToEntity(invoiceDto);

        assertNotNull(invoice);
        AssertionUtils.assertInvoice(invoice, invoiceDto);
    }

}
