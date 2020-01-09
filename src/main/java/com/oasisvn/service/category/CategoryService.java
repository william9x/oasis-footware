package com.oasisvn.service.category;

import com.oasisvn.dto.category.CategoryDTO;
import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.repository.category.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public ArrayList<CategoryDTO> getCategory() {

        try {
            ArrayList<CategoryEntity> categoryEntities = categoryRepository.findAll();

            if (categoryEntities.isEmpty()) return null;
            else {
                ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();

                for (CategoryEntity categoryEntity : categoryEntities) {
                    CategoryDTO categoryDTO = modelMapper.map(categoryEntity, CategoryDTO.class);
                    categoryDTOS.add(categoryDTO);
                }

                return categoryDTOS;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CategoryDTO getCategory(long id) {

        try {
            CategoryEntity categoryEntity = categoryRepository.findById(id);

            if (null == categoryEntity) return null;
            else {
                return modelMapper.map(categoryEntity, CategoryDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        try {
            if (isCategoryExist(categoryDTO.getTitle())) return null;
            else {
                CategoryEntity categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);
                CategoryEntity createdCategory = categoryRepository.save(categoryEntity);

                return modelMapper.map(createdCategory, CategoryDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CategoryDTO updateCategory(long id, CategoryDTO categoryDTO) {

        //Update information
        String updateTitle = categoryDTO.getTitle();

        try {
            //Old information
            CategoryEntity categoryEntity = categoryRepository.findById(id);
            String oldTitle = categoryEntity.getTitle();
            long oldId = categoryEntity.getId();

            if (null == categoryEntity) return null;
            else {
                if (isCategoryExist(updateTitle)) {
                    categoryDTO.setTitle(oldTitle);
                }

                CategoryEntity updateCategory = modelMapper.map(categoryDTO, CategoryEntity.class);
                updateCategory.setId(oldId);

                CategoryEntity updatedCategory = categoryRepository.save(updateCategory);

                return modelMapper.map(updatedCategory, CategoryDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteCategory(long id) {

        try {
            CategoryEntity categoryEntity = categoryRepository.findById(id);

            if (null == categoryEntity) return false;
            else {
                categoryRepository.delete(categoryEntity);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean isCategoryExist(String title) {

        try {
            return categoryRepository.existsByTitle(title);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
