package com.oasisvn.middleware.exception;

import com.oasisvn.middleware.exception.custom.exceptions.DuplicateRecordException;
import com.oasisvn.middleware.exception.custom.exceptions.InternalServerException;
import com.oasisvn.middleware.exception.custom.exceptions.RecordNotFoundException;
import com.oasisvn.middleware.exception.custom.exceptions.UnauthorizedException;
import com.oasisvn.middleware.exception.message.ErrorResponse;
import com.oasisvn.middleware.exception.message.OperationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<?> handleNotFoundException(RecordNotFoundException e, WebRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new OperationStatus(HttpStatus.NOT_FOUND.value(), false,
                                ErrorResponse.NO_RECORD_FOUND.getMessage(), null)

                );
    }

    @ExceptionHandler(InternalServerException.class)
    public final ResponseEntity<?> handleInternalServerException(RecordNotFoundException e, WebRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
                                e.getMessage(), null)

                );
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public final ResponseEntity<?> handleDuplicateRecordException(DuplicateRecordException e, WebRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new OperationStatus(HttpStatus.BAD_REQUEST.value(), false,
                                e.getMessage(), null)

                );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        new OperationStatus(HttpStatus.UNAUTHORIZED.value(), false,
                                ErrorResponse.AUTHENTICATION_FAILED.getMessage(), null)

                );
    }
}
