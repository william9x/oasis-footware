package com.oasisvn.io.response.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNoCategoryNoImagesResponse {
    private long id;
    private String title;
    private String subTitle;
    private String content;
    private double unitCost;
    private double unitPrice;
    private byte gender;
}
