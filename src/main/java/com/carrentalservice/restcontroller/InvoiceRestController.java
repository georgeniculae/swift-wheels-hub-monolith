package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/invoice")
public class InvoiceRestController {

    private final InvoiceService invoiceService;

    @PutMapping(path = "/update")
    public ResponseEntity<InvoiceDto> updateInvoice(@RequestBody InvoiceDto invoiceDto) {
        InvoiceDto undatedinvoiceDto = invoiceService.updateInvoice(invoiceDto);

        return ResponseEntity.ok(undatedinvoiceDto);
    }



}
