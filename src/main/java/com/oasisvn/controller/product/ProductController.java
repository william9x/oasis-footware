package com.oasisvn.controller.product;

import com.oasisvn.dto.category.CategoryDTO;
import com.oasisvn.dto.product.ProductDTO;
import com.oasisvn.io.request.category.CategoryCreateRequest;
import com.oasisvn.io.request.category.CategoryUpdateRequest;
import com.oasisvn.io.request.product.ProductCreateRequest;
import com.oasisvn.io.request.product.ProductUpdateRequest;
import com.oasisvn.io.response.ErrorResponse;
import com.oasisvn.io.response.OperationStatus;
import com.oasisvn.io.response.SuccessResponse;
import com.oasisvn.io.response.category.CategoryCreateResponse;
import com.oasisvn.io.response.category.CategoryDetailsResponse;
import com.oasisvn.io.response.product.ProductCreateResponse;
import com.oasisvn.io.response.product.ProductDetailsResponse;
import com.oasisvn.service.product.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/product")
@Api(value = "Product API")
public class ProductController {

    @Autowired
    private IProductService productService;

    @ApiOperation(value = "Get all product", response = OperationStatus.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping
    public ResponseEntity<?> getProduct(){
        OperationStatus operationStatus;

        ArrayList<ProductDTO> productDTOS = productService.getProduct();

        if (null == productDTOS) {
            operationStatus = new OperationStatus(HttpStatus.NOT_FOUND.value(), false,
                    ErrorResponse.NO_RECORD_FOUND.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operationStatus);

        } else {
            ArrayList<ProductDetailsResponse> productResponses = new ArrayList<>();

            for (ProductDTO productDTO : productDTOS) {
                ProductDetailsResponse productResponse = new ProductDetailsResponse();
                BeanUtils.copyProperties(productDTO, productResponse);
                productResponses.add(productResponse);
            }

            operationStatus = new OperationStatus(HttpStatus.OK.value(), true,
                    SuccessResponse.FOUND_RECORD.getSuccessResponse(), productResponses);

            return ResponseEntity.status(HttpStatus.OK).body(operationStatus);
        }
    }

    @ApiOperation(value = "Get a product by id", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable long id){
        OperationStatus operationStatus;

        ProductDTO productDTO = productService.getProduct(id);

        if (null == productDTO) {
            operationStatus = new OperationStatus(HttpStatus.NOT_FOUND.value(), false,
                    ErrorResponse.NO_RECORD_FOUND.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operationStatus);

        } else {
            ProductDetailsResponse productResponse = new ProductDetailsResponse();
            BeanUtils.copyProperties(productDTO, productResponse);

            operationStatus = new OperationStatus(HttpStatus.OK.value(), true,
                    SuccessResponse.FOUND_RECORD.getSuccessResponse(), productResponse);

            return ResponseEntity.status(HttpStatus.OK).body(operationStatus);
        }
    }

    @ApiOperation(value = "Create a product", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductCreateRequest request){
        OperationStatus operationStatus;

        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(request, productDTO);

        ProductDTO createdProduct = productService.createProduct(productDTO);

        if (null == createdProduct) {
            operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(),false,
                    ErrorResponse.COULD_NOT_CREATE_RECORD.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);
        } else {
            ProductCreateResponse returnValue = new ProductCreateResponse();
            BeanUtils.copyProperties(createdProduct, returnValue);

            operationStatus = new OperationStatus(HttpStatus.CREATED.value(), true,
                    SuccessResponse.CREATED_RECORD.getSuccessResponse(), returnValue);

            return ResponseEntity.status(HttpStatus.CREATED).body(operationStatus);
        }
    }

    @ApiOperation(value = "Update a product", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody @Valid ProductUpdateRequest updateRequest){
        OperationStatus operationStatus;

        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(updateRequest, productDTO);

        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);

        if (null == updatedProduct) {
            operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
                    ErrorResponse.COULD_NOT_UPDATE_RECORD.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);

        } else {
            ProductUpdateRequest returnValue = new ProductUpdateRequest();
            BeanUtils.copyProperties(updatedProduct, returnValue);

            operationStatus = new OperationStatus(HttpStatus.OK.value(), true,
                    SuccessResponse.UPDATED_RECORD.getSuccessResponse(), returnValue);

            return ResponseEntity.status(HttpStatus.OK).body(operationStatus);
        }
    }

    @ApiOperation(value = "Delete product by id", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        OperationStatus operationStatus;

        boolean deletedProduct = productService.deleteProduct(id);

        if (false == deletedProduct) {
            operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
                    ErrorResponse.COULD_NOT_DELETE_RECORD.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);

        } else {
            operationStatus = new OperationStatus(HttpStatus.OK.value(), true,
                    SuccessResponse.DELETED_RECORD.getSuccessResponse(), null);

            return ResponseEntity.status(HttpStatus.OK).body(operationStatus);
        }
    }
}
