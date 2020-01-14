package com.oasisvn.model.dto.invoice;

import com.oasisvn.model.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO implements Serializable {
    private static final long serialVersionUID = -502636537706339650L;
    private long id;
    private String invoiceUID;
    private double productValue;
    private double discountValue;
    private double totalValue;
    private byte status;
    private String orderName;
    private String orderEmail;
    private String orderPhone;
    private String orderAddress;
    private Set<ProductDTO> products = new HashSet<>();
    private List<String> productIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
