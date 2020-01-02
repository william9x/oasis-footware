package com.oasisvn.service.product;

import com.oasisvn.dto.product.ProductDTO;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.repository.product.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    private ProductEntity productEntity;
    private ProductDTO productDTO;

    @InjectMocks
    ProductService productService;

    @Mock
    IProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        productDTO = new ProductDTO();
        productDTO.setTitle("test DTO");

        productEntity = new ProductEntity();
        productEntity.setTitle("test Entity");
    }

    @Test
    void testGetAllProduct() {
        ArrayList<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);

        when(productRepository.findAllByTitleNotNull()).thenReturn(productEntities);

        ArrayList<ProductDTO> productDTOS = productService.getProduct();

        assertEquals(productDTOS.get(0).getTitle(), productEntity.getTitle());
    }

    @Test
    void testGetAllProduct_null() {
        when(productRepository.findAllByTitleNotNull()).thenReturn(new ArrayList<>());

        ArrayList<ProductDTO> productDTOS = productService.getProduct();

        assertNull(productDTOS);
    }

    @Test
    void testGetProduct() {

        //when(productRepository.findById(any(Long.class))).thenReturn(productEntity);
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}