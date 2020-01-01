package com.oasisvn.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private long id;
    private String title;
    private String created_at;
    private String updated_at;
}
