package com.oasisvn.controller.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.model.dto.category.CategoryDTO;
import com.oasisvn.service.category.CategoryService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    private CategoryDTO categoryDTO;
    private CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryDTO = new CategoryDTO();
        categoryDTO.setTitle("Test dto");

        categoryEntity = new CategoryEntity();
        categoryEntity.setTitle("Test entity");
    }

    @Test
    void getCategory() throws Exception {

        ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryDTOS.add(categoryDTO);

        when(categoryService.getCategory()).thenReturn(categoryDTOS);

        mvc.perform(get("/api/category")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value(categoryDTO.getTitle()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void GetCategoryByUID() throws Exception {

        when(categoryService.getCategory(anyString())).thenReturn(categoryDTO);

        mvc.perform(get("/api/category/testUID")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value(categoryDTO.getTitle()))
                .andDo(MockMvcResultHandlers.print());
    }

    @WithMockUser("fakeUser")
    @Test
    void createCategory() throws Exception {

        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(categoryDTO);

        mvc.perform(post("/api/category")
                .content(asJsonString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value(categoryDTO.getTitle()))
                .andDo(MockMvcResultHandlers.print());
    }



    @WithMockUser("fakeUser")
    @Test
    void updateCategory() throws Exception {
        when(categoryService.updateCategory(anyString(), any(CategoryDTO.class))).thenReturn(categoryDTO);

        mvc.perform(put("/api/category/test")
                .content(asJsonString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value(categoryDTO.getTitle()))
                .andDo(MockMvcResultHandlers.print());

    }



    @WithMockUser("fakeUser")
    @Test
    void deleteCategory() throws Exception {

        when(categoryService.deleteCategory(anyString())).thenReturn(true);

        mvc.perform(delete("/api/category/test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    void deleteCategory_unauthorized() throws Exception {
//
//        mvc.perform(delete("/api/category/test")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    void updateCategory_unauthorized() throws Exception {
//
//        mvc.perform(put("/api/category/test")
//                .content(asJsonString(categoryDTO))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    void createCategory_unauthorized() throws Exception {
//
//        mvc.perform(post("/api/category")
//                .content(asJsonString(categoryDTO))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andDo(MockMvcResultHandlers.print());
//    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}