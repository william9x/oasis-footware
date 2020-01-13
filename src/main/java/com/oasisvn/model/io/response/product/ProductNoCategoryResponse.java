package com.oasisvn.model.io.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductNoCategoryResponse {
    private String productUID;
    private String title;
    private String subTitle;
    private String content;
    private double unitCost;
    private double unitPrice;
    private byte gender;
    private List<ProductImageResponse> images = new ArrayList<>();
}
