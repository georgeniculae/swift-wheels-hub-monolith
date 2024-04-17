package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.InvoiceRequest;
import com.swiftwheelshub.dto.InvoiceResponse;
import com.swiftwheelshub.entity.BookingStatus;
import com.swiftwheelshub.entity.Car;
import com.swiftwheelshub.entity.Employee;
import com.swiftwheelshub.entity.Invoice;
import com.swiftwheelshub.entity.Revenue;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.InvoiceMapper;
import com.swiftwheelshub.mapper.InvoiceMapperImpl;
import com.swiftwheelshub.repository.InvoiceRepository;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Mock
    private CarService carService;

    @Spy
    private InvoiceMapper invoiceMapper = new InvoiceMapperImpl();

    @Test
    void updateInvoiceTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        InvoiceRequest invoiceRequest = TestUtils.getResourceAsJson("/data/InvoiceRequest.json", InvoiceRequest.class);
        Employee employee = TestUtils.getResourceAsJson("/data/Employee.json", Employee.class);
        Revenue revenue = TestUtils.getResourceAsJson("/data/Revenue.json", Revenue.class);
        Car car = TestUtils.getResourceAsJson("/data/Car.json", Car.class);

        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));
        when(employeeService.findEntityById(anyLong())).thenReturn(employee);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        when(carService.findEntityById(anyLong())).thenReturn(car);
        when(revenueService.saveEntity(any(Revenue.class))).thenReturn(revenue);

        assertDoesNotThrow(() -> invoiceService.updateInvoice(1L, invoiceRequest));

        verify(invoiceMapper, times(1)).mapEntityToDto(any(Invoice.class));
    }

    @Test
    void findAllInvoicesTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);

        when(invoiceRepository.findAll()).thenReturn(List.of(invoice));

        assertDoesNotThrow(() -> invoiceService.findAllInvoices());
        List<InvoiceResponse> invoiceResponses = invoiceService.findAllInvoices();

        AssertionUtils.assertInvoiceResponse(invoice, invoiceResponses.getFirst());
    }

    @Test
    void findAllActiveInvoicesTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        invoice.getBooking().setStatus(BookingStatus.IN_PROGRESS);

        when(invoiceRepository.findAllActiveInvoices()).thenReturn(List.of(invoice));

        assertDoesNotThrow(() -> invoiceService.findAllInvoices());
        List<InvoiceResponse> invoiceResponses = invoiceService.findAllActiveInvoices();

        AssertionUtils.assertInvoiceResponse(invoice, invoiceResponses.getFirst());
    }

    @Test
    void findAllActiveInvoicesByCustomerUsernameTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        invoice.getBooking().setStatus(BookingStatus.IN_PROGRESS);

        when(invoiceRepository.findByActiveCustomerUsername(anyString())).thenReturn(List.of(invoice));

        assertDoesNotThrow(() -> invoiceService.findAllActiveInvoicesByCustomerUsername("user"));
        List<InvoiceResponse> invoiceResponses = invoiceService.findAllActiveInvoicesByCustomerUsername("user");

        AssertionUtils.assertInvoiceResponse(invoice, invoiceResponses.getFirst());
    }

    @Test
    void findInvoiceByIdTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        invoice.getBooking().setStatus(BookingStatus.IN_PROGRESS);

        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        assertDoesNotThrow(() -> invoiceService.findInvoiceById(1L));
        InvoiceResponse invoiceResponse = invoiceService.findInvoiceById(1L);

        AssertionUtils.assertInvoiceResponse(invoice, invoiceResponse);
    }

    @Test
    void findInvoiceByIdTest_errorOnFindingById() {
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> invoiceService.findInvoiceById(1L));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Invoice with id 1 does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

    @Test
    void findInvoiceByFilterTest_success() {
        Invoice invoice = TestUtils.getResourceAsJson("/data/Invoice.json", Invoice.class);
        invoice.getBooking().setStatus(BookingStatus.IN_PROGRESS);

        when(invoiceRepository.findInvoiceByComments(anyString())).thenReturn(Optional.of(invoice));

        InvoiceResponse invoiceResponse = invoiceService.findInvoiceByComments("comment");

        AssertionUtils.assertInvoiceResponse(invoice, invoiceResponse);
    }

    @Test
    void findInvoiceByFilterTest_errorOnFindingByComments() {
        when(invoiceRepository.findInvoiceByComments(anyString())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> invoiceService.findInvoiceByComments("comment"));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Invoice with comment: comment does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

}
