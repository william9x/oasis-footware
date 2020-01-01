package com.oasisvn.repository.category;

import com.oasisvn.entity.category.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends CrudRepository<CategoryEntity, Long> {
    Boolean existsByTitle(String title);
}
