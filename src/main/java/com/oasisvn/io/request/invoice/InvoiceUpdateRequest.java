package com.oasisvn.io.request.invoice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class InvoiceUpdateRequest {

    @ApiModelProperty(
            example = "100",
            notes = "Product Value cannot be empty",
            required = true
    )
    private double productValue;

    @ApiModelProperty(
            example = "100",
            notes = "Discount Value cannot be empty",
            required = true
    )
    private double discountValue;

    @ApiModelProperty(
            example = "100",
            notes = "Total Value cannot be empty",
            required = true
    )
    private double totalValue;

    @ApiModelProperty(
            example = "1",
            notes = "Status id cannot be empty",
            required = true
    )
    @Min(1)
    @Max(3)
    private byte status;

    @ApiModelProperty(
            example = "Nam",
            notes = "Order Name cannot be empty",
            required = true
    )
    @Size(max = 50, message = "Order name can only have maximum 50 characters")
    @NotNull(message = "Order name can not be empty")
    @NotBlank(message = "Order name can not be empty")
    private String orderName;

    @ApiModelProperty(
            example = "lehoangnam@gmail.com",
            notes = "Order email cannot be empty",
            required = true
    )
    @Size(max = 50, message = "Order email can only have maximum 50 characters")
    @NotNull(message = "Order email can not be empty")
    @NotBlank(message = "Order email can not be empty")
    @Email
    private String orderEmail;

    @ApiModelProperty(
            example = "0906567897",
            notes = "Order phone cannot be empty",
            required = true
    )
    @Size(max = 15, message = "Order phone can only have maximum 15 characters")
    @NotNull(message = "Order phone can not be empty")
    @NotBlank(message = "Order phone can not be empty")
    private String orderPhone;

    @ApiModelProperty(
            example = "Hanoi",
            notes = "Order address cannot be empty",
            required = true
    )
    @Size(max = 100, message = "Order address can only have maximum 100 characters")
    @NotNull(message = "Order address can not be empty")
    @NotBlank(message = "Order address can not be empty")
    private String orderAddress;
}
