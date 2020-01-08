package com.oasisvn.io.request.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductImagesRequest {

    @NotBlank(message = "image Url required")
    @NotNull(message = "image Url required")
    @ApiModelProperty(
            example="i.imgur.com/abcdxyz",
            notes="image Url cannot be empty",
            required=true
    )
    private String imageUrl;

    @NotBlank(message = "image uid required")
    @NotNull(message = "image uid required")
    @ApiModelProperty(
            example="abcdxyz",
            notes="image uid cannot be empty",
            required=true
    )
    private String imageUID;
}
