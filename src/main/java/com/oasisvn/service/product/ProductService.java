package com.oasisvn.service.product;

import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.entity.product.ProductEntity;
import com.oasisvn.entity.product.ProductImageEntity;
import com.oasisvn.middleware.utilities.ICustomUtilities;
import com.oasisvn.model.dto.product.ProductDTO;
import com.oasisvn.model.dto.product.ProductImageDTO;
import com.oasisvn.repository.category.ICategoryRepository;
import com.oasisvn.repository.product.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ICustomUtilities utilities;

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
    public ProductDTO getProduct(String productUID) {

        try {
            ProductEntity productEntity = productRepository.findByProductUID(productUID);

            if (null == productEntity) return null;
            else {
                return modelMapper.map(productEntity, ProductDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        try {

            String requestTitle = productDTO.getTitle();
            String requestCategoryUID = productDTO.getCategory().getCategoryUID();
            CategoryEntity requestCategoryEntity = categoryRepository.findByCategoryUID(requestCategoryUID);

            if (isProductExist(requestTitle)) return null;
            else {

                for (ProductImageDTO image : productDTO.getImages()) {
                    image.setProduct(productDTO);
                }

                ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
                productEntity.setProductUID(utilities.encodeBase64UrlSafe(requestTitle));
                productEntity.setCategory(requestCategoryEntity);

                ProductEntity savedProduct = productRepository.save(productEntity);

                return modelMapper.map(savedProduct, ProductDTO.class);
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProductDTO updateProduct(String productUID, ProductDTO productDTO) {

        try {
            //Update information
            String updateTitle = productDTO.getTitle();
            List<ProductImageDTO> updateImages = productDTO.getImages();
            String updateCategoryUID = productDTO.getCategory().getCategoryUID();
            CategoryEntity updateCategory = categoryRepository.findByCategoryUID(updateCategoryUID);

            //Old information
            ProductEntity productEntity = productRepository.findByProductUID(productUID);
            String oldTitle = productEntity.getTitle();
            long oldId = productEntity.getId();
            List<ProductImageEntity> oldImages = productEntity.getImages();

            if (false == updateTitle.equals(oldTitle)) {
                if (isProductExist(updateTitle)) {
                    throw new RuntimeException("Product title already exist");
                }
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
            updateProduct.setProductUID(utilities.encodeBase64UrlSafe(updateTitle));
            updateProduct.setCategory(updateCategory);

            ProductEntity updatedProduct = productRepository.save(updateProduct);

            return modelMapper.map(updatedProduct, ProductDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteProduct(String productUID) {

        try {
            ProductEntity productEntity = productRepository.findByProductUID(productUID);

            if (null == productEntity) return false;
            else {
                productRepository.delete(productEntity);
                return true;
            }
        } catch (Exception e) {
            return false;
        }

    }

    private Boolean isProductExist(String title) {

        try {
            return productRepository.existsByTitle(title);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
