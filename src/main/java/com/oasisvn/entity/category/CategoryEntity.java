package com.oasisvn.entity.category;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    @CreatedDate
    private Date created_at;

    @Column
    @LastModifiedDate
    private Date updated_at;

}
