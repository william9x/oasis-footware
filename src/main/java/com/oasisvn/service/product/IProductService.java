package com.oasisvn.service.product;

import com.oasisvn.model.dto.product.ProductDTO;

import java.util.ArrayList;

public interface IProductService {
    ArrayList<ProductDTO> getProduct();
    ProductDTO getProduct(long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(long id, ProductDTO productDTO);
    boolean deleteProduct(long id);
}
