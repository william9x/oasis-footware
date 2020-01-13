package com.oasisvn.entity.invoice;

import com.oasisvn.entity.BaseEntity;
import com.oasisvn.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "invoices")
public class InvoiceEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8836992655369216760L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, name = "invoice_uid")
    private String invoiceUID;

    @Column(nullable = false, name = "product_value")
    private double productValue;

    @Column(nullable = false, name = "discount_value")
    private double discountValue;

    @Column(nullable = false, name = "total_value")
    private double totalValue;

    @Column(nullable = false, length = 1)
    @Min(1)
    @Max(6)
    private byte status;

    @Column(nullable = false, length = 50, name = "order_name")
    private String orderName;

    @Column(length = 50, name = "order_email")
    private String orderEmail;

    @Column(nullable = false, length = 15, name = "order_phone")
    private String orderPhone;

    @Column(nullable = false, length = 100, name = "order_address")
    private String orderAddress;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "invoice_product",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<ProductEntity> products = new HashSet<>();

    public void addProduct(ProductEntity product) {
        products.add(product);
        product.getInvoices().add(this);
    }

    public void removeTag(ProductEntity product) {
        products.remove(product);
        product.getInvoices().remove(this);
    }
}
