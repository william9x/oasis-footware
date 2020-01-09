package com.oasisvn.controller.invoice;

import com.oasisvn.dto.invoice.InvoiceDTO;
import com.oasisvn.io.response.ErrorResponse;
import com.oasisvn.io.response.OperationStatus;
import com.oasisvn.io.response.SuccessResponse;
import com.oasisvn.io.response.invoice.InvoiceDetailsResponse;
import com.oasisvn.service.invoice.IInvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/invoice")
@Api(value = "Invoice API")
public class InvoiceController {

    @Autowired
    private IInvoiceService invoiceService;

    private ModelMapper modelMapper = new ModelMapper();

    @ApiOperation(value = "Get all product", response = OperationStatus.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping
    public ResponseEntity<?> getInvoice(){
        OperationStatus operationStatus;

        ArrayList<InvoiceDTO> invoiceDTOS = invoiceService.getInvoice();

        if (null == invoiceDTOS) {
            operationStatus = new OperationStatus(HttpStatus.NOT_FOUND.value(), false,
                    ErrorResponse.NO_RECORD_FOUND.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operationStatus);

        } else {
            ArrayList<InvoiceDetailsResponse> invoiceResponses = new ArrayList<>();

            for (InvoiceDTO invoiceDTO : invoiceDTOS) {
                InvoiceDetailsResponse invoiceResponse = modelMapper.map(invoiceDTO, InvoiceDetailsResponse.class);
                invoiceResponses.add(invoiceResponse);
            }

            operationStatus = new OperationStatus(HttpStatus.OK.value(), true,
                    SuccessResponse.FOUND_RECORD.getSuccessResponse(), invoiceResponses);

            return ResponseEntity.status(HttpStatus.OK).body(operationStatus);
        }
    }
}
