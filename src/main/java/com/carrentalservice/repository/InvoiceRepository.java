package com.carrentalservice.repository;

import com.carrentalservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("From Invoice invoice " +
            "where invoice.comments like '%:comments%'")
    Invoice findInvoiceByName(@Param("comments") String comments);

}
