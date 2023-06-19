package com.carrentalservice.repository;

import com.carrentalservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("""
            From Invoice invoice
            where invoice.comments like '%:comments%'""")
    Invoice findInvoiceByName(@Param("comments") String comments);

    @Query("""
            From Invoice i
            join i.customer c
            join i.booking b
            where c.id = :customerId and
            b.status = 'IN_PROGRESS'""")
    Optional<Invoice> findByCustomerId(@Param("customerId") Long customerId);

    @Query("""
            From Invoice i
            join i.booking b
            where b.status = 'IN_PROGRESS'""")
    List<Invoice> findAllActiveInvoices();

}
