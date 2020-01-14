package com.oasisvn.service.invoice;

import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.entity.invoice.InvoiceEntity;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.middleware.utilities.ICustomUtilities;
import com.oasisvn.model.dto.category.CategoryDTO;
import com.oasisvn.model.dto.invoice.InvoiceDTO;
import com.oasisvn.model.dto.product.ProductDTO;
import com.oasisvn.repository.invoice.IInvoiceRepository;
import com.oasisvn.repository.product.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class InvoiceServiceTest {

    private CategoryEntity categoryEntity;
    private CategoryDTO categoryDTO;

    private ProductEntity productEntity;
    private ProductDTO productDTO;

    private InvoiceEntity invoiceEntity;
    private InvoiceDTO invoiceDTO;


    @InjectMocks
    InvoiceService invoiceService;

    @Mock
    private IInvoiceRepository invoiceRepository;

    @Mock
    private IProductRepository productRepository;

    @Mock
    private ICustomUtilities utilities;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryDTO = new CategoryDTO();
        categoryDTO.setTitle("test title");
        categoryDTO.setCategoryUID("testUID");

        productDTO = new ProductDTO();
        productDTO.setTitle("test DTO");
        productDTO.setCategory(categoryDTO);
        productDTO.setTitle("Test Entity");
        productDTO.setSubTitle("Test");
        productDTO.setContent("Test");
        productDTO.setUnitCost(1);
        productDTO.setUnitPrice(1);
        productDTO.setGender((byte) 1);

        categoryEntity = new CategoryEntity();
        categoryEntity.setTitle("test title");
        categoryEntity.setCategoryUID("testUID");

        productEntity = new ProductEntity();
        productEntity.setCategory(categoryEntity);
        productEntity.setTitle("Test Entity");
        productEntity.setSubTitle("Test");
        productEntity.setContent("Test");
        productEntity.setUnitCost(1);
        productEntity.setUnitPrice(1);
        productEntity.setGender((byte) 1);

        invoiceEntity = new InvoiceEntity();
        invoiceEntity.setOrderName("test ord_name");
        invoiceEntity.setOrderEmail("test@email.com");
        invoiceEntity.setOrderPhone("0909090");
        invoiceEntity.setInvoiceUID("testUID");
        invoiceEntity.setProductValue(100);
        invoiceEntity.setDiscountValue(100);
        invoiceEntity.setTotalValue(100);

        Set<ProductEntity> productEntities = new HashSet<>();
        productEntities.add(productEntity);

        invoiceEntity.setProducts(productEntities);

        invoiceDTO = new InvoiceDTO();
        invoiceDTO.setOrderName("test ord_name");
        invoiceDTO.setOrderEmail("test@email.com");
        invoiceDTO.setOrderPhone("0909090");
        invoiceDTO.setInvoiceUID("testUID");
        invoiceDTO.setProductValue(100);
        invoiceDTO.setDiscountValue(100);
        invoiceDTO.setTotalValue(100);

        Set<ProductDTO> products = new HashSet<>();
        products.add(productDTO);

        invoiceDTO.setProducts(products);

        List<String> ids = new ArrayList<>();
        ids.add("testUid1");

        invoiceDTO.setProductIds(ids);
    }

    @Test
    void getAllInvoice() {
        ArrayList<InvoiceEntity> invoiceEntities = new ArrayList<>();
        invoiceEntities.add(invoiceEntity);

        when(invoiceRepository.findAll()).thenReturn(invoiceEntities);

        ArrayList<InvoiceDTO> invoiceDTOS = invoiceService.getInvoice();

        assertEquals(invoiceDTOS.get(0).getOrderName(), invoiceEntity.getOrderName());
    }

    @Test
    void getInvoice() {

        when(invoiceRepository.findByInvoiceUID(anyString())).thenReturn(invoiceEntity);

        InvoiceDTO testInvoiceDTO = invoiceService.getInvoice("test");

        assertEquals(testInvoiceDTO.getOrderName(), invoiceEntity.getOrderName());

    }

    @Test
    void createInvoice() {
        when(utilities.generateUUID()).thenReturn("testUID");
        when(productRepository.findByProductUID(anyString())).thenReturn(productEntity);
        when(invoiceRepository.save(any(InvoiceEntity.class))).thenReturn(invoiceEntity);

        InvoiceDTO testInvoice = invoiceService.createInvoice(invoiceDTO);

        assertEquals(testInvoice.getInvoiceUID(), invoiceEntity.getInvoiceUID());
    }

    @Test
    void updateInvoice() {

        when(invoiceRepository.findByInvoiceUID(anyString())).thenReturn(invoiceEntity);
        when(invoiceRepository.save(any(InvoiceEntity.class))).thenReturn(invoiceEntity);

        InvoiceDTO testInvoice = invoiceService.updateInvoice("test", invoiceDTO);

        assertEquals(testInvoice.getStatus(), invoiceDTO.getStatus());

    }

    @Test
    void deleteInvoice() {
        when(invoiceRepository.findByInvoiceUID(anyString())).thenReturn(invoiceEntity);

        boolean testDeleteInvoice = invoiceService.deleteInvoice("test");

        assertTrue(testDeleteInvoice);
    }
}