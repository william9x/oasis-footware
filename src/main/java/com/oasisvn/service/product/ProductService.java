package com.oasisvn.service.product;

import com.oasisvn.dto.product.ProductDTO;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.repository.category.ICategoryRepository;
import com.oasisvn.repository.product.IProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public ArrayList<ProductDTO> getProduct() {
        ArrayList<ProductEntity> productEntities = productRepository.findAllByTitleNotNull();

        if (productEntities.isEmpty()) return null;
        else {
            ArrayList<ProductDTO> productDTOS = new ArrayList<>();

            for (ProductEntity productEntity : productEntities) {
                ProductDTO productDTO = new ProductDTO();
                BeanUtils.copyProperties(productEntity, productDTO);
                productDTOS.add(productDTO);
            }

            return productDTOS;
        }
    }

    @Override
    public ProductDTO getProduct(long id) {
        ProductEntity productEntity = productRepository.findById(id);

        if (null == productEntity) return null;
        else {
            ProductDTO returnValue = new ProductDTO();
            BeanUtils.copyProperties(productEntity, returnValue);

            return returnValue;
        }
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (isProductExist(productDTO.getTitle())) return null;
        else {
            ProductEntity productEntity = new ProductEntity();
            BeanUtils.copyProperties(productDTO, productEntity);

            ProductDTO returnValue = new ProductDTO();
            BeanUtils.copyProperties(productRepository.save(productEntity), returnValue);

            return returnValue;
        }
    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO productDTO) {
        long updateCategoryId = productDTO.getCategoryId();
        String updateTitle = productDTO.getTitle();
        String updateTitleSubTitle = productDTO.getSubTitle();
        String updateContent = productDTO.getContent();
        String updatedGender = productDTO.getGender();
        double updatedUnitCost = productDTO.getUnitCost();
        double updatedUnitPrice = productDTO.getUnitPrice();

        ProductEntity productEntity = productRepository.findById(id);
        if (null == productEntity) return null;
        else {
            if (productEntity.getCategoryId() != updateCategoryId) {
                productEntity.setCategoryId(updateCategoryId);
            }
            if (null != updateTitle && !isProductExist(updateTitle)) {
                productEntity.setTitle(updateTitle);
            }
            if (null != updateTitleSubTitle) {
                productEntity.setSubTitle(updateTitleSubTitle);
            }
            if (null != updateContent) {
                productEntity.setContent(updateContent);
            }
            if (null != updatedGender) {
                productEntity.setGender(updatedGender);
            }
            if (productEntity.getUnitCost() != updatedUnitCost) {
                productEntity.setUnitCost(updatedUnitCost);
            }
            if (productEntity.getUnitPrice() != updatedUnitPrice) {
                productEntity.setUnitPrice(updatedUnitPrice);
            }

            ProductDTO returnValue = new ProductDTO();
            BeanUtils.copyProperties(productRepository.save(productEntity), returnValue);

            return returnValue;
        }
    }

    @Override
    public boolean deleteProduct(long id) {
        ProductEntity productEntity = productRepository.findById(id);

        if (null == productEntity) return false;
        else {
            productRepository.delete(productEntity);
            return true;
        }
    }

    private Boolean isProductExist(String title) {
        return productRepository.existsByTitle(title);
    }

    private Boolean isCategoryExist(long id) {
        return categoryRepository.existsById(id);
    }

}
