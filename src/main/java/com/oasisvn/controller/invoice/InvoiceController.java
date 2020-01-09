package com.oasisvn.controller.invoice;

import com.oasisvn.dto.invoice.InvoiceDTO;
import com.oasisvn.io.request.invoice.InvoiceCreateRequest;
import com.oasisvn.io.request.invoice.InvoiceUpdateRequest;
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

    @ApiOperation(value = "Get product by id", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getInvoice(@PathVariable long id){
        OperationStatus operationStatus;

        InvoiceDTO invoiceDTO = invoiceService.getInvoice(id);

        if (null == invoiceDTO) {
            operationStatus = new OperationStatus(HttpStatus.NOT_FOUND.value(), false,
                    ErrorResponse.NO_RECORD_FOUND.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operationStatus);

        } else {
            InvoiceDetailsResponse invoiceDetailsResponse = modelMapper.map(invoiceDTO, InvoiceDetailsResponse.class);

            operationStatus = new OperationStatus(HttpStatus.OK.value(), true,
                    SuccessResponse.FOUND_RECORD.getSuccessResponse(), invoiceDetailsResponse);

            return ResponseEntity.status(HttpStatus.OK).body(operationStatus);
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
        OperationStatus operationStatus;

        InvoiceDTO invoiceDTO = modelMapper.map(request, InvoiceDTO.class);
        InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);

        if (null == createdInvoice) {
            operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(),false,
                    ErrorResponse.COULD_NOT_CREATE_RECORD.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);
        } else {
            InvoiceCreateRequest returnValue = modelMapper.map(createdInvoice, InvoiceCreateRequest.class);

            operationStatus = new OperationStatus(HttpStatus.CREATED.value(), true,
                    SuccessResponse.CREATED_RECORD.getSuccessResponse(), returnValue);

            return ResponseEntity.status(HttpStatus.CREATED).body(operationStatus);
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
    public ResponseEntity<?> updateInvoice(@PathVariable long id, @RequestBody @Valid InvoiceUpdateRequest updateRequest){
        OperationStatus operationStatus;

        InvoiceDTO invoiceDTO = modelMapper.map(updateRequest, InvoiceDTO.class);
        InvoiceDTO updatedInvoice = invoiceService.updateInvoice(id, invoiceDTO);

        if (null == updatedInvoice) {
            operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
                    ErrorResponse.COULD_NOT_UPDATE_RECORD.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);

        } else {
            InvoiceDetailsResponse returnValue = modelMapper.map(updatedInvoice, InvoiceDetailsResponse.class);

            operationStatus = new OperationStatus(HttpStatus.OK.value(), true,
                    SuccessResponse.UPDATED_RECORD.getSuccessResponse(), returnValue);

            return ResponseEntity.status(HttpStatus.OK).body(operationStatus);
        }
    }


    @ApiOperation(value = "Delete invoice by id", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable long id){
        OperationStatus operationStatus;

        boolean deletedProduct = invoiceService.deleteInvoice(id);

        if (false == deletedProduct) {
            operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
                    ErrorResponse.COULD_NOT_DELETE_RECORD.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);

        } else {
            operationStatus = new OperationStatus(HttpStatus.OK.value(), true,
                    SuccessResponse.DELETED_RECORD.getSuccessResponse(), null);

            return ResponseEntity.status(HttpStatus.OK).body(operationStatus);
        }
    }
}
