package com.oasisvn.io.response.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductImageResponse {
    private String imageUrl;
    private String imageUID;
}
