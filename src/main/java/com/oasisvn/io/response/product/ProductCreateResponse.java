package com.oasisvn.io.response.product;

import com.oasisvn.io.response.category.CategoryCreateResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProductCreateResponse {
    private long id;
    private CategoryCreateResponse category;
    private String title;
    private String subTitle;
    private String content;
    private double unitCost;
    private double unitPrice;
    private String gender;
    private List<ProductImageResponse> images = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
