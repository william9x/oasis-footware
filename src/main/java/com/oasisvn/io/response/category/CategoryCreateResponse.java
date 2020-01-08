package com.oasisvn.io.response.category;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryCreateResponse {
    private long id;
    private String title;
}
