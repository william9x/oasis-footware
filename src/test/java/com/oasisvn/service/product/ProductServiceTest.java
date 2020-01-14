//package com.oasisvn.service.product;
//
//import com.oasisvn.dto.product.ProductDTO;
//import com.oasisvn.entity.product.ProductEntity;
//import com.oasisvn.repository.category.ICategoryRepository;
//import com.oasisvn.repository.product.IProductRepository;
//import com.oasisvn.service.category.ICategoryService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.persistence.Column;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//class ProductServiceTest {
//
//    private ProductEntity productEntity;
//    private ProductDTO productDTO;
//
//    @InjectMocks
//    ProductService productService;
//
//    @Mock
//    IProductRepository productRepository;
//
//    @Mock
//    ICategoryRepository categoryRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        productDTO = new ProductDTO();
//        productDTO.setTitle("test DTO");
//
//        productEntity = new ProductEntity();
//        productEntity.setCategoryId(1);
//        productEntity.setTitle("Test Entity");
//        productEntity.setSubTitle("Test");
//        productEntity.setContent("Test");
//        productEntity.setUnitCost(1);
//        productEntity.setUnitPrice(1);
//        productEntity.setGender("male");
//    }
//
//    @Test
//    void testGetAllProduct() {
//        ArrayList<ProductEntity> productEntities = new ArrayList<>();
//        productEntities.add(productEntity);
//
//        when(productRepository.findAllByTitleNotNull()).thenReturn(productEntities);
//
//        ArrayList<ProductDTO> productDTOS = productService.getProduct();
//
//        assertEquals(productDTOS.get(0).getTitle(), productEntity.getTitle());
//    }
//
//    @Test
//    void testGetAllProduct_returnNull() {
//        when(productRepository.findAllByTitleNotNull()).thenReturn(new ArrayList<>());
//
//        ArrayList<ProductDTO> productDTOS = productService.getProduct();
//
//        assertNull(productDTOS);
//    }
//
//    @Test
//    void testGetProduct() {
//
//        when(productRepository.findById(1)).thenReturn(productEntity);
//
//        ProductDTO testProductDTO = productService.getProduct(1);
//
//        assertEquals(testProductDTO.getTitle(), productEntity.getTitle());
//    }
//
//    @Test
//    void testGetProduct_returnNull() {
//
//        when(productRepository.findById(1)).thenReturn(null);
//
//        ProductDTO testProductDTO = productService.getProduct(1);
//
//        assertNull(testProductDTO);
//    }
//
//    @Test
//    void createProduct() {
//
//        when(productRepository.existsByTitle(anyString())).thenReturn(false);
//        when(categoryRepository.existsById(any(Long.class))).thenReturn(true);
//        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
//
//        ProductDTO testProductDTO = productService.createProduct(productDTO);
//
//        assertEquals(testProductDTO.getTitle(), productEntity.getTitle());
//    }
//
//    @Test
//    void createProduct_returnNullWhenProductExisted() {
//
//        when(productRepository.existsByTitle(anyString())).thenReturn(true);
//
//        ProductDTO testProductDTO = productService.createProduct(productDTO);
//
//        assertNull(testProductDTO);
//    }
//
//    @Test
//    void createProduct_returnNullWhenCategoryIdNotExisted() {
//
//        when(categoryRepository.existsById(any(Long.class))).thenReturn(false);
//
//        ProductDTO testProductDTO = productService.createProduct(productDTO);
//
//        assertNull(testProductDTO);
//    }
//
//    @Test
//    void updateProduct() {
//
//        when(productRepository.findById(1)).thenReturn(productEntity);
//        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
//
//        ProductDTO testProductDTO = productService.updateProduct(1, productDTO);
//
//        assertEquals(testProductDTO.getTitle(), productEntity.getTitle());
//    }
//
//    @Test
//    void updateProduct_returnNull() {
//
//        when(productRepository.findById(1)).thenReturn(null);
//
//        ProductDTO testProductDTO = productService.updateProduct(1, productDTO);
//
//        assertNull(testProductDTO);
//    }
//
//    @Test
//    void deleteProduct() {
//
//        when(productRepository.findById(1)).thenReturn(productEntity);
//
//        boolean testDeleteProduct = productService.deleteProduct(1);
//
//        assertTrue(testDeleteProduct);
//
//    }
//
//    @Test
//    void deleteProduct_returnNull() {
//
//        when(productRepository.findById(1)).thenReturn(null);
//
//        boolean testDeleteProduct = productService.deleteProduct(1);
//
//        assertFalse(testDeleteProduct);
//
//    }
//}