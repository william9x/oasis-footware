package com.oasisvn.service.category;

import com.oasisvn.model.dto.category.CategoryDTO;

import java.util.ArrayList;

public interface ICategoryService {
    ArrayList<CategoryDTO> getCategory();
    CategoryDTO getCategory(long id);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(long id, CategoryDTO categoryDTO);
    boolean deleteCategory(long id);
}
