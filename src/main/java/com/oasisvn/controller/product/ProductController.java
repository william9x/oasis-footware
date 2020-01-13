package com.oasisvn.controller.product;

import com.oasisvn.model.dto.product.ProductDTO;
import com.oasisvn.model.io.request.product.ProductCreateRequest;
import com.oasisvn.model.io.request.product.ProductUpdateRequest;
import com.oasisvn.model.io.response.ErrorResponse;
import com.oasisvn.model.io.response.OperationStatus;
import com.oasisvn.model.io.response.SuccessResponse;
import com.oasisvn.model.io.response.product.ProductCreateResponse;
import com.oasisvn.model.io.response.product.ProductDetailsResponse;
import com.oasisvn.service.product.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper = new ModelMapper();
    private OperationStatus operationStatus = new OperationStatus();

    @ApiOperation(value = "Get all product", response = OperationStatus.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping
    public ResponseEntity<?> getProduct(){

        ArrayList<ProductDTO> productDTOS = productService.getProduct();

        if (null == productDTOS) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(operationStatus.notFoundStatus(1));

        } else {
            ArrayList<ProductDetailsResponse> productResponses = new ArrayList<>();

            for (ProductDTO productDTO : productDTOS) {
                ProductDetailsResponse productResponse = modelMapper.map(productDTO, ProductDetailsResponse.class);
                productResponses.add(productResponse);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(1, productResponses));
        }
    }

    @ApiOperation(value = "Get a product by id", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){

        ProductDTO productDTO = productService.getProduct(id);

        if (null == productDTO) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(operationStatus.notFoundStatus(1));

        } else {
            ProductDetailsResponse productResponse = modelMapper.map(productDTO, ProductDetailsResponse.class);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(1, productResponse));
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

        ProductDTO productDTO = modelMapper.map(request, ProductDTO.class);
        ProductDTO createdProduct = productService.createProduct(productDTO);

        if (null == createdProduct) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(operationStatus.internalErrorStatus(1));

        } else {
            ProductCreateResponse returnValue = modelMapper.map(createdProduct, ProductCreateResponse.class);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(operationStatus.createdStatus(returnValue));
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
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody @Valid ProductUpdateRequest updateRequest){

        ProductDTO productDTO = modelMapper.map(updateRequest, ProductDTO.class);
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);

        if (null == updatedProduct) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(operationStatus.internalErrorStatus(2));

        } else {
            ProductDetailsResponse returnValue = modelMapper.map(updatedProduct, ProductDetailsResponse.class);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(2, returnValue));
        }
    }

    @ApiOperation(value = "Delete product by id", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){

        boolean deletedProduct = productService.deleteProduct(id);

        if (false == deletedProduct) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(operationStatus.internalErrorStatus(3));

        } else {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(3, null));
        }
    }
}
