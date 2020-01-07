package com.oasisvn.entity.category;

import com.oasisvn.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "category")
public class CategoryEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5696608064487143293L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

}
