//package com.oasisvn.controller.invoice;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.oasisvn.entity.category.CategoryEntity;
//import com.oasisvn.entity.invoice.InvoiceEntity;
//import com.oasisvn.entity.product.ProductEntity;
//import com.oasisvn.model.dto.category.CategoryDTO;
//import com.oasisvn.model.dto.invoice.InvoiceDTO;
//import com.oasisvn.model.dto.product.ProductDTO;
//import com.oasisvn.service.invoice.InvoiceService;
//import com.oasisvn.service.product.ProductService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class InvoiceControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private InvoiceService invoiceService;
//
//    private CategoryEntity categoryEntity;
//    private CategoryDTO categoryDTO;
//
//    private ProductEntity productEntity;
//    private ProductDTO productDTO;
//
//    private InvoiceEntity invoiceEntity;
//    private InvoiceDTO invoiceDTO;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        categoryDTO = new CategoryDTO();
//        categoryDTO.setTitle("test title");
//        categoryDTO.setCategoryUID("testUID");
//
//        productDTO = new ProductDTO();
//        productDTO.setTitle("test DTO");
//        productDTO.setCategory(categoryDTO);
//        productDTO.setTitle("Test Entity");
//        productDTO.setSubTitle("Test");
//        productDTO.setContent("Test");
//        productDTO.setUnitCost(1);
//        productDTO.setUnitPrice(1);
//        productDTO.setGender((byte) 1);
//
//        categoryEntity = new CategoryEntity();
//        categoryEntity.setTitle("test title");
//        categoryEntity.setCategoryUID("testUID");
//
//        productEntity = new ProductEntity();
//        productEntity.setCategory(categoryEntity);
//        productEntity.setTitle("Test Entity");
//        productEntity.setSubTitle("Test");
//        productEntity.setContent("Test");
//        productEntity.setUnitCost(1);
//        productEntity.setUnitPrice(1);
//        productEntity.setGender((byte) 1);
//
//        invoiceEntity = new InvoiceEntity();
//        invoiceEntity.setOrderName("test ord_name");
//        invoiceEntity.setOrderEmail("test@email.com");
//        invoiceEntity.setOrderPhone("0909090");
//        invoiceEntity.setInvoiceUID("testUID");
//        invoiceEntity.setProductValue(100);
//        invoiceEntity.setDiscountValue(100);
//        invoiceEntity.setTotalValue(100);
//
//        Set<ProductEntity> productEntities = new HashSet<>();
//        productEntities.add(productEntity);
//
//        invoiceEntity.setProducts(productEntities);
//
//        invoiceDTO = new InvoiceDTO();
//        invoiceDTO.setOrderName("test ord_name");
//        invoiceDTO.setOrderEmail("test@email.com");
//        invoiceDTO.setOrderPhone("0909090");
//        invoiceDTO.setInvoiceUID("testUID");
//        invoiceDTO.setProductValue(100);
//        invoiceDTO.setDiscountValue(100);
//        invoiceDTO.setTotalValue(100);
//
//        Set<ProductDTO> products = new HashSet<>();
//        products.add(productDTO);
//
//        invoiceDTO.setProducts(products);
//
//        List<String> ids = new ArrayList<>();
//        ids.add("testUid1");
//
//        invoiceDTO.setProductIds(ids);
//    }
//
//    @Test
//    void getInvoice() throws Exception {
//        ArrayList<InvoiceDTO> invoiceDTOS = new ArrayList<>();
//        invoiceDTOS.add(invoiceDTO);
//
//        when(invoiceService.getInvoice()).thenReturn(invoiceDTOS);
//
//        mvc.perform(get("/api/invoice")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].orderName").value(invoiceDTO.getOrderName()))
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    void testGetInvoice() throws Exception {
//        when(invoiceService.getInvoice(anyString())).thenReturn(invoiceDTO);
//
//        mvc.perform(get("/api/invoice/test")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.orderName").value(invoiceDTO.getOrderName()))
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @WithMockUser("fakeUser")
//    @Test
//    void createInvoice() throws Exception {
//        when(invoiceService.createInvoice(any(InvoiceDTO.class))).thenReturn(invoiceDTO);
//
//        mvc.perform(post("/api/invoice")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.orderName").value(invoiceDTO.getOrderName()))
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @WithMockUser("fakeUser")
//    @Test
//    void updateInvoice() throws Exception {
//        when(invoiceService.updateInvoice(anyString(), any(InvoiceDTO.class))).thenReturn(invoiceDTO);
//
//        mvc.perform(put("/api/invoice/test")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.orderName").value(invoiceDTO.getOrderName()))
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @WithMockUser("fakeUser")
//    @Test
//    void deleteInvoice() throws Exception {
//        when(invoiceService.deleteInvoice(anyString())).thenReturn(true);
//
//        mvc.perform(delete("/api/invoice/test")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    void createInvoice_unauthorized() throws Exception {
//
//        mvc.perform(post("/api/invoice")
//                .content(asJsonString(categoryDTO))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(MockMvcResultHandlers.print());
//    }
//    @Test
//    void updateInvoice_unauthorized() throws Exception {
//
//        mvc.perform(put("/api/invoice/test")
//                .content(asJsonString(categoryDTO))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    void deleteInvoice_unauthorized() throws Exception {
//
//        mvc.perform(delete("/api/invoice/test")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}