package com.oasisvn.service.invoice;

import com.oasisvn.model.dto.invoice.InvoiceDTO;

import java.util.ArrayList;

public interface IInvoiceService {
    ArrayList<InvoiceDTO> getInvoice();
    InvoiceDTO getInvoice(String invoiceUID);
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO updateInvoice(String invoiceUID, InvoiceDTO invoiceDTO);
    boolean deleteInvoice(String invoiceUID);
}
