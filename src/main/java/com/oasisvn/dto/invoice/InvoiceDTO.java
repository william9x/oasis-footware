package com.oasisvn.dto.invoice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class InvoiceDTO implements Serializable {
    private static final long serialVersionUID = -502636537706339650L;
    private long id;
    private double productValue;
    private double discountValue;
    private double totalValue;
    private byte status;
    private String orderName;
    private String orderEmail;
    private String orderPhone;
    private String orderAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
