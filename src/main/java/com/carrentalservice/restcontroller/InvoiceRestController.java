package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/invoice")
@CrossOrigin(origins = "*")
public class InvoiceRestController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> findAllInvoices() {
        List<InvoiceDto> allInvoices = invoiceService.findAllInvoices();

        return ResponseEntity.ok(allInvoices);
    }

    @GetMapping(path = "/active")
    public ResponseEntity<List<InvoiceDto>> findAllActiveInvoices() {
        List<InvoiceDto> allInvoices = invoiceService.findAllActiveInvoices();

        return ResponseEntity.ok(allInvoices);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InvoiceDto> findInvoiceById(@RequestParam("id") Long id) {
        InvoiceDto invoice = invoiceService.findInvoiceById(id);

        return ResponseEntity.ok(invoice);
    }

    @GetMapping(path = "/by-customer/{customerId}")
    public ResponseEntity<List<InvoiceDto>> findAllInvoicesByCustomerId(@RequestParam("customerId") Long customerId) {
        List<InvoiceDto> allInvoices = invoiceService.findAllInvoicesByCustomerId(customerId);

        return ResponseEntity.ok(allInvoices);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<InvoiceDto> updateInvoice(@RequestBody InvoiceDto invoiceDto) {
        InvoiceDto undatedinvoiceDto = invoiceService.updateInvoice(invoiceDto);

        return ResponseEntity.ok(undatedinvoiceDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<InvoiceDto> deleteInvoiceById(@RequestParam("id") Long id) {
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
