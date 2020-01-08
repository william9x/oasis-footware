package com.oasisvn.service.product;

import com.oasisvn.dto.product.ProductDTO;
import com.oasisvn.dto.product.ProductImageDTO;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.entity.product.ProductImageEntity;
import com.oasisvn.repository.category.ICategoryRepository;
import com.oasisvn.repository.product.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public ArrayList<ProductDTO> getProduct() {

        try {
            ArrayList<ProductEntity> productEntities = productRepository.findAll();

            if (productEntities.isEmpty()) return null;
            else {
                ArrayList<ProductDTO> productDTOS = new ArrayList<>();

                for (ProductEntity productEntity : productEntities) {
                    ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
                    productDTOS.add(productDTO);
                }

                return productDTOS;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProductDTO getProduct(long id) {

        try {
            ProductEntity productEntity = productRepository.findById(id);

            if (null == productEntity) return null;
            else {
                ProductDTO returnValue = modelMapper.map(productEntity, ProductDTO.class);
                return returnValue;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (isProductExist(productDTO.getTitle()) || !isCategoryExist(productDTO.getCategoryId())) return null;
        else {

            for (ProductImageDTO image : productDTO.getImages()) {
                image.setProduct(productDTO);
            }

            ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);

            try {
                ProductEntity savedProduct = productRepository.save(productEntity);
                ProductDTO returnValue = modelMapper.map(savedProduct, ProductDTO.class);

                return returnValue;

            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO productDTO) {

        //Update information
        long updateCategoryId = productDTO.getCategoryId();
        String updateTitle = productDTO.getTitle();
        List<ProductImageDTO> updateImages = productDTO.getImages();

        try {
            //Old information
            ProductEntity productEntity = productRepository.findById(id);
            long oldId = productEntity.getId();
            long oldCategoryId = productEntity.getCategoryId();
            String oldTitle = productEntity.getTitle();
            List<ProductImageEntity> oldImages = productEntity.getImages();

            if (null == productEntity) return null;
            else {
                if (false == isCategoryExist(updateCategoryId)) {
                    productDTO.setCategoryId(oldCategoryId);
                }
                if (isProductExist(updateTitle)) {
                    productDTO.setTitle(oldTitle);
                }
                if (null != updateImages && 0 != updateImages.size()) {
                    for (ProductImageDTO updateImage : updateImages) {
                        updateImage.setProduct(productDTO);
                    }
                } else {
                    for (ProductImageEntity oldImage : oldImages) {
                        updateImages.add(modelMapper.map(oldImage, ProductImageDTO.class));
                    }
                }

                ProductEntity updateProduct = modelMapper.map(productDTO, ProductEntity.class);
                updateProduct.setId(oldId);

                ProductEntity updatedProduct = productRepository.save(updateProduct);
                ProductDTO returnValue = modelMapper.map(updatedProduct, ProductDTO.class);

                return returnValue;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteProduct(long id) {
        ProductEntity productEntity = productRepository.findById(id);

        if (null == productEntity) return false;
        else {
            try {
                productRepository.delete(productEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    private Boolean isProductExist(String title) {
        return productRepository.existsByTitle(title);
    }

    private Boolean isCategoryExist(long id) {
        return categoryRepository.existsById(id);
    }

}
