package com.oasisvn.model.io.response;

import lombok.Getter;

@Getter
public enum SuccessResponse {
    FOUND_RECORD("Found record with provided information"),
    AUTHENTICATED("Authentication successful"),
    CREATED_RECORD("Create record successfully"),
    UPDATED_RECORD("Update record successfully"),
    DELETED_RECORD("Delete record successfully");

    private String successResponse;
    SuccessResponse(String successResponse){
        this.successResponse = successResponse;
    }
}
