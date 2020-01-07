package com.oasisvn.entity.image;

import com.oasisvn.entity.BaseEntity;
import com.oasisvn.entity.product.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "product_image")
public class ImageEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 142079658480744197L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String image_url;

    @Column(nullable = false, unique = true)
    private String image_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Column(nullable = false)
    private ProductEntity product;
}
