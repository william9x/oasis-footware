package com.oasisvn.middleware.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationStatus {
    private int status;
    private boolean success;
    private String message;
    private Object data;

    public OperationStatus(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.success = false;
        this.message = (String) errorAttributes.get("error");
        this.data = null;
    }

   public OperationStatus internalErrorStatus(Integer errorCase) {

       String errorMessage = "";

       switch (errorCase) {
           case 1:
               errorMessage = ErrorResponse.COULD_NOT_CREATE_RECORD.getMessage();
               break;
           case 2:
               errorMessage = ErrorResponse.COULD_NOT_UPDATE_RECORD.getMessage();
               break;
           case 3:
               errorMessage = ErrorResponse.COULD_NOT_DELETE_RECORD.getMessage();
               break;
       }

       return new OperationStatus(
               HttpStatus.INTERNAL_SERVER_ERROR.value(),
               false,
               errorMessage,
               null);
   }

   public OperationStatus notFoundStatus(Integer errorCase) {

       String errorMessage = "";

       switch (errorCase) {
           case 1:
               errorMessage = ErrorResponse.NO_RECORD_FOUND.getMessage();
               break;
           case 2:
               errorMessage = ErrorResponse.AUTHENTICATION_FAILED.getMessage();
               break;
       }

       return new OperationStatus(
               HttpStatus.NOT_FOUND.value(),
               false,
               errorMessage,
               null
       );
   }

    public OperationStatus okStatus(Integer successCase, Object data){

        String successMessage = "";

        switch (successCase) {
            case 1:
                successMessage = SuccessResponse.FOUND_RECORD.getSuccessResponse();
                break;
            case 2:
                successMessage = SuccessResponse.UPDATED_RECORD.getSuccessResponse();
                break;
            case 3:
                successMessage = SuccessResponse.DELETED_RECORD.getSuccessResponse();
                break;
            case 4:
                successMessage = SuccessResponse.AUTHENTICATED.getSuccessResponse();
                break;
        }

        return new OperationStatus(
                HttpStatus.OK.value(),
                true,
                successMessage,
                data
        );
    }

    public OperationStatus createdStatus(Object data) {
        return new OperationStatus(
                HttpStatus.CREATED.value(),
                true,
                SuccessResponse.CREATED_RECORD.getSuccessResponse(),
                data
        );
    }
}
