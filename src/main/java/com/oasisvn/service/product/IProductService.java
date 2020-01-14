package com.oasisvn.service.product;

import com.oasisvn.model.dto.product.ProductDTO;

import java.util.ArrayList;

public interface IProductService {
    ArrayList<ProductDTO> getProduct();
    ProductDTO getProduct(String productUID);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(String productUID, ProductDTO productDTO);
    boolean deleteProduct(String productUID);
}
