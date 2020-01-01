package com.oasisvn.io.request.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryUpdateRequest {

    @NotBlank(message = "Title required")
    @NotNull(message = "Title required")
    @Size(max = 100, message = "Title can only have maximum 100 characters")
    @ApiModelProperty(
            example="Leather",
            notes="Title cannot be empty",
            required=true
    )
    private String title;
}
