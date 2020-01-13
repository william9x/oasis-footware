package com.oasisvn.repository.invoice;

import com.oasisvn.entity.invoice.InvoiceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IInvoiceRepository extends CrudRepository<InvoiceEntity, Long> {
    ArrayList<InvoiceEntity> findAll();
    InvoiceEntity findById(long id);
    InvoiceEntity findByInvoiceUID(String invoiceUID);
}
