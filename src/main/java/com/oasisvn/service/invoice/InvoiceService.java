package com.oasisvn.service.invoice;

import com.oasisvn.dto.invoice.InvoiceDTO;
import com.oasisvn.dto.product.ProductDTO;
import com.oasisvn.entity.invoice.InvoiceEntity;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.repository.invoice.IInvoiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public ArrayList<InvoiceDTO> getInvoice() {

        try {
            ArrayList<InvoiceEntity> invoiceEntities = invoiceRepository.findAll();

            if (invoiceEntities.isEmpty()) return null;
            else {
                ArrayList<InvoiceDTO> invoiceDTOS = new ArrayList<>();

                for (InvoiceEntity invoiceEntity : invoiceEntities) {
                    InvoiceDTO invoiceDTO = modelMapper.map(invoiceEntity, InvoiceDTO.class);
                    invoiceDTOS.add(invoiceDTO);
                }

                return invoiceDTOS;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public InvoiceDTO getInvoice(long id) {
        return null;
    }

    @Override
    public boolean deleteInvoice(long id) {
        return false;
    }

    @Override
    public InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO) {
        return null;
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        return null;
    }
}
