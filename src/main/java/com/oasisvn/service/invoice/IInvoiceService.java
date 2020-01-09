package com.oasisvn.service.invoice;

import com.oasisvn.dto.invoice.InvoiceDTO;

import java.util.ArrayList;

public interface IInvoiceService {
    ArrayList<InvoiceDTO> getInvoice();

    InvoiceDTO getInvoice(long id);

    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

    InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO);

    boolean deleteInvoice(long id);
}
