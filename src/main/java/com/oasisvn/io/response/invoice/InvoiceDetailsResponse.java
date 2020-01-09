package com.oasisvn.io.response.invoice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDetailsResponse {
    private long id;
    private double productValue;
    private double discountValue;
    private double totalValue;
    private byte status;
    private String orderName;
    private String orderEmail;
    private String orderPhone;
    private String orderAddress;
}
