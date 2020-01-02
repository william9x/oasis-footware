package com.oasisvn.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO {
    private long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
