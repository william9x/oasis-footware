package com.oasisvn.io.request.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductCreateRequest {
    @NotBlank(message = "Category id required")
    @NotNull(message = "Category id required")
    @ApiModelProperty(
            example = "1",
            notes = "Category id cannot be empty",
            required = true
    )
    private String categoryId;

    @NotBlank(message = "Title required")
    @NotNull(message = "Title required")
    @Size(max = 100, message = "Title can only have maximum 100 characters")
    @ApiModelProperty(
            example = "Leather",
            notes = "Title cannot be empty",
            required = true
    )
    private String title;

    @NotBlank(message = "Sub title required")
    @NotNull(message = "Sub title required")
    @Size(max = 100, message = "Sub title can only have maximum 100 characters")
    @ApiModelProperty(
            example = "This product has sub title",
            notes = "Sub title cannot be empty",
            required = true
    )
    private String subTitle;

    @NotBlank(message = "Content required")
    @NotNull(message = "Content required")
    @ApiModelProperty(
            example = "This product has content",
            notes = "Content cannot be empty",
            required = true
    )
    private String content;

    @NotNull(message = "Unit cost required")
    @ApiModelProperty(
            example = "100",
            notes = "Unit cost cannot be empty",
            required = true
    )
    private double unitCost;

    @NotNull(message = "Unit price required")
    @ApiModelProperty(
            example = "100",
            notes = "Unit price cannot be empty",
            required = true
    )
    private double unitPrice;

    @NotBlank(message = "Gender required")
    @NotNull(message = "Gender required")
    @Size(max = 6, message = "Gender can only have maximum 6 characters")
    @ApiModelProperty(
            example = "Female",
            notes = "Gender cannot be empty",
            required = true
    )
    private String gender;
}
