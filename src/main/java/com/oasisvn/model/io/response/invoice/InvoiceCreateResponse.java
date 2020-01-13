package com.oasisvn.model.io.response.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreateResponse {
    private String invoiceUID;
    private double productValue;
    private double discountValue;
    private double totalValue;
    private byte status;
    private String orderName;
    private String orderEmail;
    private String orderPhone;
    private String orderAddress;
    private Set<InvoiceProductResponse> products;
}
