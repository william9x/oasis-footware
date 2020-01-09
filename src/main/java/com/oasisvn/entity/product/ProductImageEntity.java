package com.oasisvn.entity.product;

import com.oasisvn.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "product_image")
public class ProductImageEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 142079658480744197L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    @Column(nullable = false, unique = true, name = "image_uid")
    private String imageUID;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;
}
