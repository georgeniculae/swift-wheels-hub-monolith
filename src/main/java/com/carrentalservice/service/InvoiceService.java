package com.carrentalservice.service;

import com.carrentalservice.dto.InvoiceDto;
import com.carrentalservice.entity.Invoice;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.InvoiceMapper;
import com.carrentalservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public void updateInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findAllByCustomerId(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }

    public Invoice findEntityById(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            return optionalInvoice.get();
        }

        throw new NotFoundException("Invoice with id " + id + "does not exist");
    }

    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public Long countInvoice() {
        return invoiceRepository.count();
    }

    public Invoice findInvoiceByName(String searchString) {
        return invoiceRepository.findInvoiceByName(searchString);
    }

    public List<InvoiceDto> findAllActiveInvoices() {
        return invoiceRepository.findAllActiveInvoices()
                .stream()
                .map(invoiceMapper::mapEntityToDto)
                .toList();
    }

    public int countAllActiveInvoices() {
        return findAllActiveInvoices().size();
    }

}
