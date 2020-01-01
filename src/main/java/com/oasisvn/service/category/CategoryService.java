package com.oasisvn.service.category;

import com.oasisvn.dto.category.CategoryDTO;
import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.repository.category.ICategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        if (isCategoryExist(categoryDTO.getTitle())) return null;
        else {
            CategoryEntity categoryEntity = new CategoryEntity();
            BeanUtils.copyProperties(categoryDTO, categoryEntity);

            CategoryDTO returnValue = new CategoryDTO();
            BeanUtils.copyProperties(categoryRepository.save(categoryEntity), returnValue);

            return returnValue;
        }

    }

    private Boolean isCategoryExist(String title) {
        return categoryRepository.existsByTitle(title);
    }
}
