package com.oasisvn.entity.product;

import com.oasisvn.entity.BaseEntity;
import com.oasisvn.entity.category.CategoryEntity;
import com.oasisvn.entity.invoice.InvoiceEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "product")
public class ProductEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2222593543117030062L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, name = "sub_title")
    private String subTitle;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, name = "unit_cost")
    private double unitCost;

    @Column(nullable = false, name = "unit_price")
    private double unitPrice;

    @Column(nullable = false, length = 1)
    @Min(1)
    @Max(2)
    private byte gender;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductImageEntity> images = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    Set<InvoiceEntity> invoices = new HashSet<>();
}
