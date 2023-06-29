package com.carrentalservice.service;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.entity.Invoice;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.mapper.InvoiceMapper;
import com.carrentalservice.mapper.InvoiceMapperImpl;
import com.carrentalservice.repository.InvoiceRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private RevenueService revenueService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Spy
    private InvoiceMapper invoiceMapper = new InvoiceMapperImpl();

    @Test
    void updateInvoiceTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        InvoiceDto invoiceDto = TestUtils.getResourceAsJson("/data/InvoiceDto.json", InvoiceDto.class);
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);
        Revenue revenue = TestUtils.getResourceAsJson("/data/Revenue.json", Revenue.class);

        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));
        when(employeeService.findEntityById(anyLong())).thenReturn(employee);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        when(revenueService.saveEntity(any(Revenue.class))).thenReturn(revenue);

        assertDoesNotThrow(() -> invoiceService.updateInvoice(invoiceDto));

        verify(invoiceMapper, times(1)).mapEntityToDto(any(Invoice.class));
    }

    @Test
    void findAllInvoicesTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);

        when(invoiceRepository.findAll()).thenReturn(List.of(invoice));

        assertDoesNotThrow(() -> invoiceService.findAllInvoices());
        List<InvoiceDto> invoiceDtoList = invoiceService.findAllInvoices();

        AssertionUtils.assertInvoice(invoice, invoiceDtoList.get(0));
    }

}
