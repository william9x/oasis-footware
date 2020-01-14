package com.oasisvn.service.category;

import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.middleware.exception.custom.exceptions.DuplicateRecordException;
import com.oasisvn.middleware.exception.custom.exceptions.InternalServerException;
import com.oasisvn.middleware.exception.message.ErrorResponse;
import com.oasisvn.middleware.utilities.ICustomUtilities;
import com.oasisvn.model.dto.category.CategoryDTO;
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
    public CategoryDTO getCategory(String categoryUID) {

        try {
            CategoryEntity categoryEntity = categoryRepository.findByCategoryUID(categoryUID);

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
            String requestTitle = categoryDTO.getTitle();

            if (isCategoryExist(requestTitle)) return null;
            else {
                CategoryEntity categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);
                categoryEntity.setCategoryUID(utilities.encodeBase64UrlSafe(requestTitle));

                CategoryEntity createdCategory = categoryRepository.save(categoryEntity);

                return modelMapper.map(createdCategory, CategoryDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CategoryDTO updateCategory(String categoryUID, CategoryDTO categoryDTO) {

        //Update information
        String updateTitle = categoryDTO.getTitle();

        try {
            //Old information
            CategoryEntity categoryEntity = categoryRepository.findByCategoryUID(categoryUID);
            String oldTitle = categoryEntity.getTitle();
            long oldId = categoryEntity.getId();

            if (null == categoryEntity) return null;
            else {
                if (isCategoryExist(updateTitle)) {
                    categoryDTO.setTitle(oldTitle);
                }

                CategoryEntity updateCategory = modelMapper.map(categoryDTO, CategoryEntity.class);
                updateCategory.setCategoryUID(utilities.encodeBase64UrlSafe(updateTitle));
                updateCategory.setId(oldId);

                CategoryEntity updatedCategory = categoryRepository.save(updateCategory);

                return modelMapper.map(updatedCategory, CategoryDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteCategory(String categoryUID) {

        try {
            CategoryEntity categoryEntity = categoryRepository.findByCategoryUID(categoryUID);

            if (null == categoryEntity) return false;
            if (categoryEntity.getProducts().size() > 0) throw new InternalServerException(ErrorResponse.CATEGORY_HAS_CHILD.getMessage());
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
            throw new DuplicateRecordException(ErrorResponse.RECORD_ALREADY_EXIST.getMessage());
        }
    }
}
