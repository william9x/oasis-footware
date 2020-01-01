package com.oasisvn.entity.product;

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
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String category_id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String sub_title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private double unit_cost;

    @Column(nullable = false)
    private double unit_price;

    @Column(nullable = false, length = 6)
    private String gender;

    @Column
    @CreatedDate
    private Date created_at;

    @Column
    @LastModifiedDate
    private Date updated_at;

}
