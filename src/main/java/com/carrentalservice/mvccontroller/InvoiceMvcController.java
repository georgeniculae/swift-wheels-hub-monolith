package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.service.EmployeeService;
import com.carrentalservice.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class InvoiceMvcController {

    private final InvoiceService invoiceService;
    private final EmployeeService employeeService;

    @GetMapping(path = "/invoice/active-invoices")
    public String showActiveInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.findAllActiveInvoices());
        model.addAttribute("numberOfInvoices", invoiceService.countAllActiveInvoices());

        return "active-invoice";
    }

    @GetMapping(path = "/invoices")
    public String showInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.findAllInvoices());
        model.addAttribute("numberOfInvoices", invoiceService.countInvoices());

        return "invoice-list";
    }

    @GetMapping(path = "/invoice/delete/{id}")
    public String deleteInvoiceById(@PathVariable("id") Long id) {
        invoiceService.deleteInvoiceById(id);

        return "redirect:/invoices";
    }

    @PostMapping(path = "/invoice/update")
    public String editInvoice(@ModelAttribute("invoice") @Valid InvoiceDto invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-invoice";
        }

        invoiceService.updateInvoice(invoice);

        return "redirect:/invoices";
    }

    @GetMapping(path = "/invoice/edit/{id}")
    public String showInvoiceEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("invoice", invoiceService.findInvoiceById(id));
        model.addAttribute("employees", employeeService.findAllEmployees());

        return "edit-invoice";
    }

}
