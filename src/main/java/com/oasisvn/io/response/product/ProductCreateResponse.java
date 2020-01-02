package com.oasisvn.io.response.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ProductCreateResponse {
    private long id;
    private long categoryId;
    private String title;
    private String subTitle;
    private String content;
    private double unitCost;
    private double unitPrice;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
