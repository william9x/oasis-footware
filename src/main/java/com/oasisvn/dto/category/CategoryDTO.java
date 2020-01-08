package com.oasisvn.dto.category;

import com.oasisvn.io.response.product.ProductDetailsResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = -2683088575543840719L;
    private long id;
    private String title;
    private List<ProductDetailsResponse> products = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
