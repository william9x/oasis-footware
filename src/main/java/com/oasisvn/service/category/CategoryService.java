package com.oasisvn.service.category;

import com.oasisvn.dto.category.CategoryDTO;
import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.repository.category.ICategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public ArrayList<CategoryDTO> getCategory() {
        ArrayList<CategoryEntity> categoryEntities = categoryRepository.findAllByTitleNotNull();

        if (categoryEntities.isEmpty()) return null;
        else {
            ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();

            for (CategoryEntity categoryEntity : categoryEntities) {
                CategoryDTO categoryDTO = new CategoryDTO();
                BeanUtils.copyProperties(categoryEntity, categoryDTO);
                categoryDTOS.add(categoryDTO);
            }

            return categoryDTOS;
        }
    }

    @Override
    public CategoryDTO getCategory(long id) {

        CategoryEntity categoryEntity = categoryRepository.findById(id);

        if (null == categoryEntity) return null;
        else {
            CategoryDTO returnValue = new CategoryDTO();
            BeanUtils.copyProperties(categoryEntity, returnValue);

            return returnValue;
        }
    }

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

    @Override
    public CategoryDTO updateCategory(long id, CategoryDTO categoryDTO) {
        String updateTitle = categoryDTO.getTitle();

        CategoryEntity categoryEntity = categoryRepository.findById(id);
        if (null == categoryEntity) return null;
        else {
            if (null != updateTitle && !isCategoryExist(updateTitle)) {
                categoryEntity.setTitle(updateTitle);
            }

            CategoryDTO returnValue = new CategoryDTO();
            BeanUtils.copyProperties(categoryRepository.save(categoryEntity), returnValue);

            return returnValue;
        }
    }

    @Override
    public boolean deleteCategory(long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id);

        if (null == categoryEntity) return false;
        else {
            categoryRepository.delete(categoryEntity);
            return true;
        }
    }

    private Boolean isCategoryExist(String title) {
        return categoryRepository.existsByTitle(title);
    }
}
