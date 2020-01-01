package com.oasisvn.io.response.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateResponse {
    private String category_id;
    private String title;
    private String sub_title;
    private String content;
    private double unit_cost;
    private double unit_price;
    private String gender;
}
