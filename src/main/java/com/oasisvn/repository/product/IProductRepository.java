package com.oasisvn.repository.product;

import com.oasisvn.entity.product.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IProductRepository extends CrudRepository<ProductEntity, Long> {
    ArrayList<ProductEntity> findAll();
    ProductEntity findById(long id);
    Boolean existsByTitle(String title);
}
