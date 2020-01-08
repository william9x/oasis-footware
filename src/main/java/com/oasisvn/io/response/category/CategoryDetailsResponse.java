package com.oasisvn.io.response.category;

import com.oasisvn.io.response.product.ProductDetailsResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDetailsResponse {
    private long id;
    private String title;
    private List<ProductDetailsResponse> products = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
