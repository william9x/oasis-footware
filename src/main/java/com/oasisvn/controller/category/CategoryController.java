package com.oasisvn.controller.category;

import com.oasisvn.dto.category.CategoryDTO;
import com.oasisvn.io.request.category.CategoryCreateRequest;
import com.oasisvn.io.response.SuccessResponse;
import com.oasisvn.io.response.category.CategoryCreateResponse;
import com.oasisvn.io.response.ErrorResponse;
import com.oasisvn.io.response.OperationStatus;
import com.oasisvn.service.category.ICategoryService;
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

@RestController
@RequestMapping("api/category")
@Api(value = "Category API")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String getCategory(){
        return "This is my category";
    }

    @ApiOperation(value = "Create a category", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryCreateRequest request){
        OperationStatus operationStatus;

        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(request, categoryDTO);

        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);

        if (null == createdCategory) {
            operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(),false,
                    ErrorResponse.COULD_NOT_CREATE_RECORD.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);
        } else {
            CategoryCreateResponse returnValue = new CategoryCreateResponse();
            BeanUtils.copyProperties(createdCategory, returnValue);

            operationStatus = new OperationStatus(HttpStatus.CREATED.value(), true,
                    SuccessResponse.CREATED_RECORD.getSuccessResponse(), returnValue);

            return ResponseEntity.status(HttpStatus.CREATED).body(operationStatus);
        }
    }

    @PutMapping(path = "{categoryId}")
    public String updateCategory(@PathVariable String categoryId){
        return categoryId;
    }

    @DeleteMapping(path = "{categoryId}")
    public String deleteCategory(@PathVariable String categoryId){
        return categoryId;
    }
}
