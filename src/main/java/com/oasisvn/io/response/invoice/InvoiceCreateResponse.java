package com.oasisvn.io.response.invoice;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InvoiceCreateResponse {
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
