package com.oasisvn.io.response.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDetailsResponse {
    private long id;
    private String categoryId;
    private String title;
    private String subTitle;
    private String content;
    private double unitCost;
    private double unitPrice;
    private String gender;
    private Date createdAt;
    private Date updatedAt;
}
