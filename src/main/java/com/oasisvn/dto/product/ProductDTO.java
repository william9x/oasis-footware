package com.oasisvn.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = -5875817431235428416L;
    private long id;
    private long categoryId;
    private String title;
    private String subTitle;
    private String content;
    private double unitCost;
    private double unitPrice;
    private String gender;
    private List<ProductImageDTO> images = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
