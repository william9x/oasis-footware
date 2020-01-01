package com.oasisvn.io.response.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDetailsResponse {
    private long id;
    private String category_id;
    private String title;
    private String sub_title;
    private String content;
    private double unit_cost;
    private double unit_price;
    private String gender;
    private Date created_at;
    private Date updated_at;
}
