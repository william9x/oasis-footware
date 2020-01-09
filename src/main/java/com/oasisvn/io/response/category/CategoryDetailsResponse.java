package com.oasisvn.io.response.category;

import com.oasisvn.io.response.product.ProductNoCategoryNoImagesResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDetailsResponse {
    private long id;
    private String title;
    private List<ProductNoCategoryNoImagesResponse> products = new ArrayList<>();
}
