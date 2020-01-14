package com.oasisvn.service.category;

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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    private CategoryEntity categoryEntity;
    private CategoryDTO categoryDTO;

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    ICategoryRepository categoryRepository;

    @Mock
    private ICustomUtilities utilities;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryDTO = new CategoryDTO();
        categoryDTO.setTitle("Test dto");

        categoryEntity = new CategoryEntity();
        categoryEntity.setTitle("Test entity");
    }

    @Test
    void testGetAllCategory() {

        ArrayList<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(categoryEntity);

        when(categoryRepository.findAll()).thenReturn(categoryEntities);

        ArrayList<CategoryDTO> categoryDTOS = categoryService.getCategory();

        assertEquals(categoryDTOS.get(0).getTitle(), categoryEntity.getTitle());

    }

    @Test
    void testGetCategory() {

        when(categoryRepository.findByCategoryUID(anyString())).thenReturn(categoryEntity);

        CategoryDTO categoryDTO = categoryService.getCategory("test");

        assertEquals(categoryDTO.getTitle(), categoryEntity.getTitle());
    }

    @Test
    void createCategory() {

        when(categoryRepository.existsByTitle(anyString())).thenReturn(false);
        when(utilities.encodeBase64UrlSafe(anyString())).thenReturn("test");
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);

        assertEquals(createdCategory.getTitle(), categoryEntity.getTitle());
    }

    @Test
    void updateCategory() {

        when(categoryRepository.findByCategoryUID(anyString())).thenReturn(categoryEntity);
        when(categoryRepository.existsByTitle(anyString())).thenReturn(false);
        when(utilities.encodeBase64UrlSafe(anyString())).thenReturn("test");
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        CategoryDTO createdCategory = categoryService.updateCategory("test", categoryDTO);

        assertEquals(createdCategory.getTitle(), categoryEntity.getTitle());
    }

    @Test
    void deleteCategory() {

        when(categoryRepository.findByCategoryUID(anyString())).thenReturn(categoryEntity);

        assertTrue(categoryService.deleteCategory("test"));
    }
}