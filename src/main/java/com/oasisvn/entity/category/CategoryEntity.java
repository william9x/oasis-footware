package com.oasisvn.entity.category;

import com.oasisvn.entity.BaseEntity;
import com.oasisvn.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "categories")
public class CategoryEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5696608064487143293L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, name = "category_uid")
    private String categoryUID;

    @Column(nullable = false, unique = true)
    private String title;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductEntity> products = new ArrayList<>();

}
