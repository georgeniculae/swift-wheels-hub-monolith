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
public class InvoiceRestController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> findAllInvoices() {
        List<InvoiceDto> allInvoices = invoiceService.findAllInvoices();

        return ResponseEntity.ok(allInvoices);
    }

    @GetMapping(path = "/invoice/{customerId}")
    public ResponseEntity<List<InvoiceDto>> findAllInvoicesByCustomerId(@RequestParam("customerId") Long customerId) {
        List<InvoiceDto> allInvoices = invoiceService.findAllInvoicesByCustomerId(customerId);

        return ResponseEntity.ok(allInvoices);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<InvoiceDto> updateInvoice(@RequestBody InvoiceDto invoiceDto) {
        InvoiceDto undatedinvoiceDto = invoiceService.updateInvoice(invoiceDto);

        return ResponseEntity.ok(undatedinvoiceDto);
    }

}
