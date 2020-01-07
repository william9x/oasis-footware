package com.oasisvn.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = -2683088575543840719L;
    private long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
