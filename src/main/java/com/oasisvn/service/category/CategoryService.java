package com.oasisvn.service.category;

import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.middleware.utilities.ICustomUtilities;
import com.oasisvn.model.dto.category.CategoryDTO;
import com.oasisvn.model.dto.product.ProductDTO;
import com.oasisvn.repository.category.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ICustomUtilities utilities;

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
    public CategoryDTO getCategory(String categoryId) {

        try {
            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);

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
                categoryEntity.setCategoryId(utilities.encodeBase64UrlSafe(categoryEntity.getTitle()));

                CategoryEntity createdCategory = categoryRepository.save(categoryEntity);

                return modelMapper.map(createdCategory, CategoryDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CategoryDTO updateCategory(String categoryId, CategoryDTO categoryDTO) {

        try {
            //Update information
            String updateTitle = categoryDTO.getTitle();

            if (isCategoryExist(updateTitle)) throw new RuntimeException("Title existed");

            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);

            categoryEntity.setTitle(updateTitle);
            categoryEntity.setCategoryId(utilities.encodeBase64UrlSafe(updateTitle));

            CategoryEntity updatedCategory = categoryRepository.save(categoryEntity);

            return modelMapper.map(updatedCategory, CategoryDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteCategory(String categoryId) {

        try {
            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);

            categoryRepository.delete(categoryEntity);
            return true;
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
