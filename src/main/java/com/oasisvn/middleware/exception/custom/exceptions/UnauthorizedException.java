package com.oasisvn.middleware.exception.custom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 2938846119747003595L;

    public UnauthorizedException(){}
}
