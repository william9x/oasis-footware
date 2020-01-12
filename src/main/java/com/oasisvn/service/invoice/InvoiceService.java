package com.oasisvn.service.invoice;

import com.oasisvn.model.dto.invoice.InvoiceDTO;
import com.oasisvn.entity.invoice.InvoiceEntity;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.model.io.response.ErrorResponse;
import com.oasisvn.repository.invoice.IInvoiceRepository;
import com.oasisvn.repository.product.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IProductRepository productRepository;

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

        try {
            InvoiceEntity invoiceEntity = invoiceRepository.findById(id);

            if (null == invoiceEntity) return null;
            else {
                return modelMapper.map(invoiceEntity, InvoiceDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {

        try {
            InvoiceEntity invoiceEntity = modelMapper.map(invoiceDTO, InvoiceEntity.class);

            for (Integer id : invoiceDTO.getProductIds()) {
                ProductEntity product = productRepository.findById(id);
                if (null == product) throw new RuntimeException(String.valueOf(ErrorResponse.NO_RECORD_FOUND));
                invoiceEntity.addProduct(product);
            }

            InvoiceEntity createdInvoice = invoiceRepository.save(invoiceEntity);

            return modelMapper.map(createdInvoice, InvoiceDTO.class);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO) {

        try {
            //Old information
            InvoiceEntity invoiceEntity = invoiceRepository.findById(id);
            Set<ProductEntity> oldProducts = invoiceEntity.getProducts();

            if (null == invoiceEntity) return null;
            else {

                InvoiceEntity updateInvoice = modelMapper.map(invoiceDTO, InvoiceEntity.class);
                updateInvoice.setId(invoiceEntity.getId());

                List<Integer> updateProductIds = invoiceDTO.getProductIds();
                if (null != updateProductIds && 0 != updateProductIds.size()) {
                    for (Integer productId : updateProductIds) {
                        ProductEntity product = productRepository.findById(productId);
                        if (null == product) throw new RuntimeException(String.valueOf(ErrorResponse.NO_RECORD_FOUND));
                        updateInvoice.addProduct(product);
                    }
                } else {
                    updateInvoice.setProducts(oldProducts);
                }


                InvoiceEntity updatedInvoice = invoiceRepository.save(updateInvoice);
                return modelMapper.map(updatedInvoice, InvoiceDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteInvoice(long id) {
        try {
            InvoiceEntity invoiceEntity = invoiceRepository.findById(id);

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
