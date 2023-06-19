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

    public InvoiceDto updateInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceMapper.mapDtoToEntity(invoiceDto);
        Invoice savedInvoice = invoiceRepository.save(invoice);

        return invoiceMapper.mapEntityToDto(savedInvoice);
    }

    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findAllByCustomerId(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }

    public InvoiceDto findInvoiceById(Long id) {
        Invoice invoice = findEntityById(id);

        return invoiceMapper.mapEntityToDto(invoice);
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
