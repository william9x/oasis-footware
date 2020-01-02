package com.oasisvn.io.response;

import lombok.Getter;


@Getter
public enum ErrorResponse {
    MISSING_REQUIRED_FIELD("Missing required field"),
    RECORD_ALREADY_EXIST("Record already exist"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record with provided information was not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_CREATE_RECORD("Could not create record"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    UNSUPPORTED_MEDIA_TYPE("Unsupported media type, try adding Content-Type: application header.");

    private String errorMessage;
    ErrorResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

}
