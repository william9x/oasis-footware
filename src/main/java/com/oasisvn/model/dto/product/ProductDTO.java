package com.oasisvn.model.dto.product;

import com.oasisvn.model.dto.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = -5875817431235428416L;
    private long id;
    private CategoryDTO category;
    private String productUID;
    private String title;
    private String subTitle;
    private String content;
    private double unitCost;
    private double unitPrice;
    private byte gender;
    private List<ProductImageDTO> images = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
