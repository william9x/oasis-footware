package com.oasisvn.io.request.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryCreateRequest {

    @NotBlank(message = "Title required")
    @NotNull(message = "Title required")
    private String title;
}
