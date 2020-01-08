package com.oasisvn.io.response.product;

import com.oasisvn.io.response.category.CategoryCreateResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDetailsResponse {
    private long id;
    private CategoryCreateResponse category;
    private String title;
    private String subTitle;
    private String content;
    private double unitCost;
    private double unitPrice;
    private String gender;
    private List<ProductImageResponse> images = new ArrayList<>();
}
