package com.oasisvn.model.io.request.invoice;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceUpdateRequest {

    @ApiModelProperty(
            example = "1",
            notes = "Status id cannot be empty",
            required = true
    )
    @Min(1)
    @Max(6)
    private byte status;


}
