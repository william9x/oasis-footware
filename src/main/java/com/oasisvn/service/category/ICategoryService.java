package com.oasisvn.service.category;

import com.oasisvn.model.dto.category.CategoryDTO;

import java.util.ArrayList;

public interface ICategoryService {
    ArrayList<CategoryDTO> getCategory();
    CategoryDTO getCategory(String categoryUID);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(String categoryUID, CategoryDTO categoryDTO);
    boolean deleteCategory(String categoryUID);
}
