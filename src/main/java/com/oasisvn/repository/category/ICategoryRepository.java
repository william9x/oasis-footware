package com.oasisvn.repository.category;

import com.oasisvn.entity.category.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ICategoryRepository extends CrudRepository<CategoryEntity, Long> {
    ArrayList<CategoryEntity> findAll();
    CategoryEntity findByCategoryId(String categoryId);
    Boolean existsByTitle(String title);
}
