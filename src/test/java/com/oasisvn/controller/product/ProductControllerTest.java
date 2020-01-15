package com.oasisvn.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.model.dto.category.CategoryDTO;
import com.oasisvn.model.dto.product.ProductDTO;
import com.oasisvn.model.dto.product.ProductImageDTO;
import com.oasisvn.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    private CategoryEntity categoryEntity;
    private CategoryDTO categoryDTO;

    private ProductEntity productEntity;
    private ProductDTO productDTO;

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

        ProductImageDTO image = new ProductImageDTO();
        image.setImageUID("TestUID");
        image.setImageUrl("TestURL");

        List<ProductImageDTO> images = new ArrayList<>();
        images.add(image);

        productDTO.setImages(images);

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
    void getAllProduct() throws Exception {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS.add(productDTO);

        when(productService.getProduct()).thenReturn(productDTOS);

        mvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value(productDTO.getTitle()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getProduct() throws Exception {

        when(productService.getProduct(anyString())).thenReturn(productDTO);

        mvc.perform(get("/api/product/testUID")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value(productDTO.getTitle()))
                .andDo(MockMvcResultHandlers.print());

    }

    @WithMockUser("fakeUser")
    @Test
    void createProduct() throws Exception {
        when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);

        mvc.perform(post("/api/product")
                .content(asJsonString(productDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value(productDTO.getTitle()))
                .andDo(MockMvcResultHandlers.print());

    }

    @WithMockUser("fakeUser")
    @Test
    void updateCategory() throws Exception {
        when(productService.updateProduct(anyString(), any(ProductDTO.class))).thenReturn(productDTO);

        mvc.perform(put("/api/product/test")
                .content(asJsonString(productDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value(productDTO.getTitle()))
                .andDo(MockMvcResultHandlers.print());
    }

    @WithMockUser("fakeUser")
    @Test
    void deleteProduct() throws Exception {

        when(productService.deleteProduct(anyString())).thenReturn(true);

        mvc.perform(delete("/api/product/test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createProduct_unauthorized() throws Exception {

        mvc.perform(post("/api/product")
                .content(asJsonString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void updateProduct_unauthorized() throws Exception {

        mvc.perform(put("/api/product/test")
                .content(asJsonString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    void deleteProduct_unauthorized() throws Exception {

        mvc.perform(delete("/api/product/test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}