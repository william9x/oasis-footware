package com.oasisvn.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CategoryDTO {
    private long id;
    private String title;
    private Date created_at;
    private Date updated_at;
}
