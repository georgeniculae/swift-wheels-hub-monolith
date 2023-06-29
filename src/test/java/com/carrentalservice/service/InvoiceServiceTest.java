package com.carrentalservice.service;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.entity.BookingStatus;
import com.carrentalservice.entity.Employee;
import com.carrentalservice.entity.Invoice;
import com.carrentalservice.entity.Revenue;
import com.carrentalservice.exception.NotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void findAllActiveInvoicesTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        invoice.getBooking().setStatus(BookingStatus.IN_PROGRESS);

        when(invoiceRepository.findAllActiveInvoices()).thenReturn(List.of(invoice));

        assertDoesNotThrow(() -> invoiceService.findAllInvoices());
        List<InvoiceDto> invoiceDtoList = invoiceService.findAllActiveInvoices();

        AssertionUtils.assertInvoice(invoice, invoiceDtoList.get(0));
    }

    @Test
    void findAllInvoicesByCustomerIdTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        invoice.getBooking().setStatus(BookingStatus.IN_PROGRESS);

        when(invoiceRepository.findByCustomerId(anyLong())).thenReturn(List.of(invoice));

        assertDoesNotThrow(() -> invoiceService.findAllInvoicesByCustomerId(1L));
        List<InvoiceDto> invoiceDtoList = invoiceService.findAllInvoicesByCustomerId(1L);

        AssertionUtils.assertInvoice(invoice, invoiceDtoList.get(0));
    }

    @Test
    void findInvoiceByIdTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        invoice.getBooking().setStatus(BookingStatus.IN_PROGRESS);

        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        assertDoesNotThrow(() -> invoiceService.findInvoiceById(1L));
        InvoiceDto invoiceDto = invoiceService.findInvoiceById(1L);

        AssertionUtils.assertInvoice(invoice, invoiceDto);
    }

    @Test
    void findInvoiceByIdTest_errorOnFindingById() {
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> invoiceService.findInvoiceById(1L));

        assertNotNull(notFoundException);
        assertEquals("Invoice with id 1 does not exist", notFoundException.getMessage());
    }

    @Test
    void findInvoiceByFilterTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        invoice.getBooking().setStatus(BookingStatus.IN_PROGRESS);

        when(invoiceRepository.findInvoiceByComments(anyString())).thenReturn(Optional.of(invoice));

        assertDoesNotThrow(() -> invoiceService.findInvoiceByComments("comment"));
        InvoiceDto invoiceDto = invoiceService.findInvoiceByComments("comment");

        AssertionUtils.assertInvoice(invoice, invoiceDto);
    }

}
