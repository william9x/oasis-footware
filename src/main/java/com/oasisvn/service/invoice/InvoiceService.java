package com.oasisvn.service.invoice;

import com.oasisvn.entity.invoice.InvoiceEntity;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.middleware.utilities.ICustomUtilities;
import com.oasisvn.model.dto.invoice.InvoiceDTO;
import com.oasisvn.repository.invoice.IInvoiceRepository;
import com.oasisvn.repository.product.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICustomUtilities utilities;

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
    public InvoiceDTO getInvoice(String invoiceUID) {

        try {

            InvoiceEntity invoiceEntity = invoiceRepository.findByInvoiceUID(invoiceUID);
            return modelMapper.map(invoiceEntity, InvoiceDTO.class);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {

        try {
            InvoiceEntity invoiceEntity = modelMapper.map(invoiceDTO, InvoiceEntity.class);
            invoiceEntity.setInvoiceUID(utilities.generateUUID());

            for (String productUID : invoiceDTO.getProductIds()) {
                ProductEntity product = productRepository.findByProductUID(productUID);
                invoiceEntity.addProduct(product);
            }


            InvoiceEntity createdInvoice = invoiceRepository.save(invoiceEntity);

            return modelMapper.map(createdInvoice, InvoiceDTO.class);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public InvoiceDTO updateInvoice(String invoiceUID, InvoiceDTO invoiceDTO) {

        try {

            InvoiceEntity invoiceEntity = invoiceRepository.findByInvoiceUID(invoiceUID);
            invoiceEntity.setStatus(invoiceDTO.getStatus());

            InvoiceEntity updatedInvoice = invoiceRepository.save(invoiceEntity);
            return modelMapper.map(updatedInvoice, InvoiceDTO.class);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteInvoice(String invoiceUID) {
        try {
            InvoiceEntity invoiceEntity = invoiceRepository.findByInvoiceUID(invoiceUID);

            if (null == invoiceEntity) return false;
            else {
                invoiceRepository.delete(invoiceEntity);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
