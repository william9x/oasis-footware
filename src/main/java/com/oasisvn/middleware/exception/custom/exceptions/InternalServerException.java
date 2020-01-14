package com.oasisvn.middleware.exception.custom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    private static final long serialVersionUID = 997466823227162859L;

    public InternalServerException(String message) {
        super(message);
    }
}
