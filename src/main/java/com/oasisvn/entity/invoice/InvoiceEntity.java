package com.oasisvn.entity.invoice;

import com.oasisvn.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "invoice")
public class InvoiceEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8836992655369216760L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "product_value")
    private double productValue;

    @Column(nullable = false, name = "discount_value")
    private double discountValue;

    @Column(nullable = false, name = "total_value")
    private double totalValue;

    @Column(nullable = false, length = 1)
    @Min(1)
    @Max(3)
    private byte status;

    @Column(nullable = false, length = 50, name = "order_name")
    private String orderName;

    @Column(length = 50, name = "order_email")
    private String orderEmail;

    @Column(nullable = false, length = 15, name = "order_phone")
    private String orderPhone;

    @Column(nullable = false, length = 100, name = "order_address")
    private String orderAddress;
}
