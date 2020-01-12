package com.oasisvn.model.io.response.category;

import com.oasisvn.model.io.response.product.ProductDetailsResponse;
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
public class CategoryDetailsResponse {
    private long id;
    private String title;
    private List<ProductDetailsResponse> products = new ArrayList<>();
}
