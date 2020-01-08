package com.oasisvn.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductImageDTO implements Serializable {
    private static final long serialVersionUID = -7890112682052596834L;
    private long id;
    private String imageUrl;
    private String imageUID;
    private ProductDTO product;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
