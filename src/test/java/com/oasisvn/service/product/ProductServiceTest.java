package com.oasisvn.service.product;

import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.middleware.utilities.ICustomUtilities;
import com.oasisvn.model.dto.category.CategoryDTO;
import com.oasisvn.model.dto.product.ProductDTO;
import com.oasisvn.repository.category.ICategoryRepository;
import com.oasisvn.repository.product.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    private CategoryEntity categoryEntity;
    private CategoryDTO categoryDTO;
    private ProductEntity productEntity;
    private ProductDTO productDTO;

    @InjectMocks
    ProductService productService;

    @Mock
    IProductRepository productRepository;

    @Mock
    ICategoryRepository categoryRepository;

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
    }

    @Test
    void testGetAllProduct() {
        ArrayList<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);

        when(productRepository.findAll()).thenReturn(productEntities);

        ArrayList<ProductDTO> productDTOS = productService.getProduct();

        assertEquals(productDTOS.get(0).getTitle(), productEntity.getTitle());
    }

    @Test
    void testGetAllProduct_returnNull() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        ArrayList<ProductDTO> productDTOS = productService.getProduct();

        assertNull(productDTOS);
    }

    @Test
    void testGetProduct() {

        when(productRepository.findByProductUID(anyString())).thenReturn(productEntity);

        ProductDTO testProductDTO = productService.getProduct("test");

        assertEquals(testProductDTO.getTitle(), productEntity.getTitle());
    }

    @Test
    void testGetProduct_returnNull() {

        when(productRepository.findById(1)).thenReturn(null);

        ProductDTO testProductDTO = productService.getProduct("test");

        assertNull(testProductDTO);
    }

    @Test
    void createProduct() {

        when(productRepository.existsByTitle(anyString())).thenReturn(false);
        when(categoryRepository.existsByCategoryUID(anyString())).thenReturn(true);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        ProductDTO testProductDTO = productService.createProduct(productDTO);

        assertEquals(testProductDTO.getTitle(), productEntity.getTitle());
    }

    @Test
    void createProduct_returnNullWhenProductExisted() {

        when(productRepository.existsByTitle(anyString())).thenReturn(true);

        ProductDTO testProductDTO = productService.createProduct(productDTO);

        assertNull(testProductDTO);
    }

    @Test
    void createProduct_returnNullWhenCategoryIdNotExisted() {

        when(categoryRepository.existsById(any(Long.class))).thenReturn(false);

        ProductDTO testProductDTO = productService.createProduct(productDTO);

        assertNull(testProductDTO);
    }

    @Test
    void updateProduct() {

        when(categoryRepository.findByCategoryUID(anyString())).thenReturn(categoryEntity);
        when(productRepository.findByProductUID(anyString())).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productRepository.existsByTitle(anyString())).thenReturn(false);
        when(utilities.encodeBase64UrlSafe(anyString())).thenReturn("test");

        ProductDTO testProductDTO = productService.updateProduct("test", productDTO);

        assertEquals(testProductDTO.getTitle(), productEntity.getTitle());
    }

    @Test
    void updateProduct_returnNull() {

        when(productRepository.findByProductUID(anyString())).thenReturn(null);

        ProductDTO testProductDTO = productService.updateProduct("test", productDTO);

        assertNull(testProductDTO);
    }

    @Test
    void deleteProduct() {

        when(productRepository.findByProductUID(anyString())).thenReturn(productEntity);

        boolean testDeleteProduct = productService.deleteProduct("test");

        assertTrue(testDeleteProduct);

    }

    @Test
    void deleteProduct_returnNull() {

        when(productRepository.findById(1)).thenReturn(null);

        boolean testDeleteProduct = productService.deleteProduct("test");

        assertFalse(testDeleteProduct);

    }
}