package com.oasisvn.controller.invoice;

import com.oasisvn.model.dto.invoice.InvoiceDTO;
import com.oasisvn.model.io.request.invoice.InvoiceCreateRequest;
import com.oasisvn.model.io.request.invoice.InvoiceUpdateRequest;
import com.oasisvn.model.io.response.ErrorResponse;
import com.oasisvn.model.io.response.OperationStatus;
import com.oasisvn.model.io.response.SuccessResponse;
import com.oasisvn.model.io.response.invoice.InvoiceCreateResponse;
import com.oasisvn.model.io.response.invoice.InvoiceDetailsResponse;
import com.oasisvn.service.invoice.IInvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/invoice")
@Api(value = "Invoice API")
public class InvoiceController {

    @Autowired
    private IInvoiceService invoiceService;

    private ModelMapper modelMapper = new ModelMapper();
    OperationStatus operationStatus = new OperationStatus();

    @ApiOperation(value = "Get all product", response = OperationStatus.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping
    public ResponseEntity<?> getInvoice(){

        ArrayList<InvoiceDTO> invoiceDTOS = invoiceService.getInvoice();

        if (null == invoiceDTOS) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(operationStatus.notFoundStatus(1));

        } else {
            ArrayList<InvoiceDetailsResponse> invoiceResponses = new ArrayList<>();

            for (InvoiceDTO invoiceDTO : invoiceDTOS) {
                InvoiceDetailsResponse invoiceResponse = modelMapper.map(invoiceDTO, InvoiceDetailsResponse.class);
                invoiceResponses.add(invoiceResponse);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(1, invoiceResponses));
        }
    }

    @ApiOperation(value = "Get product by id", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getInvoice(@PathVariable String id){

        InvoiceDTO invoiceDTO = invoiceService.getInvoice(id);

        if (null == invoiceDTO) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(operationStatus.notFoundStatus(1));

        } else {
            InvoiceDetailsResponse invoiceDetailsResponse = modelMapper.map(invoiceDTO, InvoiceDetailsResponse.class);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(1, invoiceDetailsResponse));
        }
    }

    @ApiOperation(value = "Create an invoice", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody @Valid InvoiceCreateRequest request){

        InvoiceDTO invoiceDTO = modelMapper.map(request, InvoiceDTO.class);
        InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);

        if (null == createdInvoice) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(operationStatus.internalErrorStatus(1));

        } else {
            InvoiceCreateResponse returnValue = modelMapper.map(createdInvoice, InvoiceCreateResponse.class);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(operationStatus.createdStatus(returnValue));
        }
    }

    @ApiOperation(value = "Update an invoice", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable String id, @RequestBody @Valid InvoiceUpdateRequest updateRequest){

        InvoiceDTO invoiceDTO = modelMapper.map(updateRequest, InvoiceDTO.class);
        InvoiceDTO updatedInvoice = invoiceService.updateInvoice(id, invoiceDTO);

        if (null == updatedInvoice) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(operationStatus.internalErrorStatus(2));

        } else {
            InvoiceDetailsResponse returnValue = modelMapper.map(updatedInvoice, InvoiceDetailsResponse.class);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(2, returnValue));
        }
    }


    @ApiOperation(value = "Delete invoice by id", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable String id){

        boolean deletedProduct = invoiceService.deleteInvoice(id);

        if (false == deletedProduct) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(operationStatus.internalErrorStatus(3));

        } else {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(operationStatus.okStatus(3, null));
        }
    }
}
