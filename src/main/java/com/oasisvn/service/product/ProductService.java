package com.oasisvn.service.product;

import com.oasisvn.dto.product.ProductDTO;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.repository.product.IProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

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
        String updateCategoryId = productDTO.getCategory_id();
        String updateTitle = productDTO.getTitle();
        String updateTitleSubTitle = productDTO.getSub_title();
        String updateContent = productDTO.getContent();
        double updatedUnitCost = productDTO.getUnit_cost();
        double updatedUnitPrice = productDTO.getUnit_price();
        String updatedGender = productDTO.getGender();

        ProductEntity productEntity = productRepository.findById(id);
        if (null == productEntity) return null;
        else {
            if (null != updateCategoryId) {
                productEntity.setCategory_id(updateCategoryId);
            }
            if (null != updateTitle && !isProductExist(updateTitle)) {
                productEntity.setTitle(updateTitle);
            }
            if (null != updateTitleSubTitle) {
                productEntity.setSub_title(updateTitleSubTitle);
            }
            if (null != updateContent) {
                productEntity.setContent(updateContent);
            }
            if (productEntity.getUnit_cost() != updatedUnitCost) {
                productEntity.setUnit_cost(updatedUnitCost);
            }
            if (productEntity.getUnit_price() != updatedUnitPrice) {
                productEntity.setUnit_price(updatedUnitPrice);
            }
            if (null != updatedGender) {
                productEntity.setGender(updatedGender);
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

}
