package com.carrentalservice.restcontroller;

import com.carrentalservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InvoiceRestController {

    private final InvoiceService invoiceService;

}
