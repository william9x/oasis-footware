package com.oasisvn.io.response.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateResponse {
    private String categoryId;
    private String title;
    private String subTitle;
    private String content;
    private double unitCost;
    private double unitPrice;
    private String gender;
}
