package com.oasisvn.controller;

import com.oasisvn.io.response.ErrorResponse;
import com.oasisvn.io.response.OperationStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Based on the helpful answer at http://stackoverflow.com/q/25356781/56285,
 * with error details in response body added.
 *
 * @author Joni Karppinen
 * @since 20.2.2015
 */

@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(value = ERROR_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public OperationStatus handleError() {
        return new OperationStatus(HttpStatus.NOT_FOUND.value(), false,
                ErrorResponse.INVALID_PATH.getErrorMessage(), null);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
