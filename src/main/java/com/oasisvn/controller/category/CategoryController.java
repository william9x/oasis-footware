package com.oasisvn.controller.category;

import com.oasisvn.model.dto.category.CategoryDTO;
import com.oasisvn.model.io.request.category.CategoryCreateRequest;
import com.oasisvn.model.io.request.category.CategoryUpdateRequest;
import com.oasisvn.model.io.response.OperationStatus;
import com.oasisvn.model.io.response.SuccessResponse;
import com.oasisvn.model.io.response.category.CategoryCreateResponse;
import com.oasisvn.model.io.response.category.CategoryDetailsResponse;
import com.oasisvn.service.category.ICategoryService;
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
@RequestMapping("api/category")
@Api(value = "Category API")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    private OperationStatus operationStatus = new OperationStatus();
    private ModelMapper modelMapper = new ModelMapper();

    @ApiOperation(value = "Get all category", response = OperationStatus.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping
    public ResponseEntity<?> getCategory(){

        ArrayList<CategoryDTO> categoryDTOS = categoryService.getCategory();

        if (null == categoryDTOS) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(operationStatus.notFoundStatus(1));

        } else {
            ArrayList<CategoryDetailsResponse> categoryResponses = new ArrayList<>();

            for (CategoryDTO categoryDTO : categoryDTOS) {
                CategoryDetailsResponse categoryResponse = modelMapper.map(categoryDTO, CategoryDetailsResponse.class);
                categoryResponses.add(categoryResponse);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(1, categoryResponses));
        }
    }

    @ApiOperation(value = "Get a category by uid", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCategory(@PathVariable String id){

        CategoryDTO categoryDTO = categoryService.getCategory(id);

        if (null == categoryDTO) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(operationStatus.notFoundStatus(1));

        } else {
            CategoryDetailsResponse categoryResponse = modelMapper.map(categoryDTO, CategoryDetailsResponse.class);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(1, categoryResponse));
        }
    }

    @ApiOperation(value = "Create a category", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryCreateRequest request){

        CategoryDTO categoryDTO = modelMapper.map(request, CategoryDTO.class);
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);

        if (null == createdCategory) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(operationStatus.internalErrorStatus(1));

        } else {
            CategoryCreateResponse returnValue = modelMapper.map(createdCategory, CategoryCreateResponse.class);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.createdStatus(returnValue));
        }
    }

    @ApiOperation(value = "Update a category", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody @Valid CategoryUpdateRequest updateRequest){

        CategoryDTO categoryDTO = modelMapper.map(updateRequest, CategoryDTO.class);
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);

        if (null == updatedCategory) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(operationStatus.internalErrorStatus(2));

        } else {
            CategoryDetailsResponse returnValue = modelMapper.map(updatedCategory, CategoryDetailsResponse.class);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(2, returnValue));
        }
    }

    @ApiOperation(value = "Delete category by id", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id){

        boolean deletedCategory = categoryService.deleteCategory(id);

        if (false == deletedCategory) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(operationStatus.internalErrorStatus(3));

        } else {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(3, null));

        }
    }
}
