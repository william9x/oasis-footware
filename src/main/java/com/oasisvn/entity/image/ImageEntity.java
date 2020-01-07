package com.oasisvn.entity.image;

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

    @Column(nullable = false)
    private String product_id;
}
