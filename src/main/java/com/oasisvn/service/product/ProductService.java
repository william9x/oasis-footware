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
                return modelMapper.map(productEntity, ProductDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        try {
            if (isProductExist(productDTO.getTitle()) || !isCategoryExist(productDTO.getCategory().getId())) return null;
            else {

                for (ProductImageDTO image : productDTO.getImages()) {
                    image.setProduct(productDTO);
                }

                ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
                ProductEntity savedProduct = productRepository.save(productEntity);

                return modelMapper.map(savedProduct, ProductDTO.class);
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO productDTO) {

        //Update information
        long updateCategoryId = productDTO.getCategory().getId();
        String updateTitle = productDTO.getTitle();
        List<ProductImageDTO> updateImages = productDTO.getImages();

        try {
            //Old information
            ProductEntity productEntity = productRepository.findById(id);
            long oldId = productEntity.getId();
            long oldCategoryId = productEntity.getCategory().getId();
            String oldTitle = productEntity.getTitle();
            List<ProductImageEntity> oldImages = productEntity.getImages();

            if (null == productEntity) return null;
            else {
                if (false == isCategoryExist(updateCategoryId)) {
                    throw new RuntimeException("Category does not exist");
                }
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

                ProductEntity updatedProduct = productRepository.save(updateProduct);

                return modelMapper.map(updatedProduct, ProductDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteProduct(long id) {

        try {
            ProductEntity productEntity = productRepository.findById(id);

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

    private Boolean isCategoryExist(long id) {

        try {
            return categoryRepository.existsById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
