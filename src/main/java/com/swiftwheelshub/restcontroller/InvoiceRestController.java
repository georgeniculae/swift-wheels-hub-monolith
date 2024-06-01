package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.InvoiceRequest;
import com.swiftwheelshub.dto.InvoiceResponse;
import com.swiftwheelshub.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/invoices")
public class InvoiceRestController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> findAllInvoices() {
        List<InvoiceResponse> invoiceResponses = invoiceService.findAllInvoices();

        return ResponseEntity.ok(invoiceResponses);
    }

    @GetMapping(path = "/active")
    public ResponseEntity<List<InvoiceResponse>> findAllActiveInvoices() {
        List<InvoiceResponse> activeInvoiceResponses = invoiceService.findAllActiveInvoices();

        return ResponseEntity.ok(activeInvoiceResponses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InvoiceResponse> findInvoiceById(@PathVariable("id") Long id) {
        InvoiceResponse invoiceResponse = invoiceService.findInvoiceById(id);

        return ResponseEntity.ok(invoiceResponse);
    }

    @GetMapping(path = "/by-customer/{customerUsername}")
    public ResponseEntity<List<InvoiceResponse>> findAllActiveInvoicesByCustomerUsername(@PathVariable("customerUsername") String customerUsername) {
        List<InvoiceResponse> invoiceResponses = invoiceService.findAllActiveInvoicesByCustomerUsername(customerUsername);

        return ResponseEntity.ok(invoiceResponses);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<InvoiceResponse> updateInvoice(@PathVariable("id") Long id, @RequestBody InvoiceRequest invoiceRequest) {
        InvoiceResponse undatedInvoiceResponse = invoiceService.updateInvoice(id, invoiceRequest);

        return ResponseEntity.ok(undatedInvoiceResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteInvoiceById(@PathVariable("id") Long id) {
        invoiceService.deleteInvoiceById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> countInvoices() {
        Long invoices = invoiceService.countInvoices();

        return ResponseEntity.ok(invoices);
    }

    @GetMapping(path = "/active-count")
    public ResponseEntity<Long> countActiveInvoices() {
        Long invoices = invoiceService.countAllActiveInvoices();

        return ResponseEntity.ok(invoices);
    }

}
