package com.oasisvn.entity.product;

import com.oasisvn.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "product")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "category_id")
    private long categoryId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable   = false, name = "sub_title")
    private String subTitle;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, name = "unit_cost")
    private double unitCost;

    @Column(nullable = false, name = "unit_price")
    private double unitPrice;

    @Column(nullable = false, length = 6)
    private String gender;

}
