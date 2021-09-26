package com.assignment.api.exception.dto;

import com.assignment.api.exception.ErrorPrinter;
import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum implements ErrorPrinter {

    INVALID_PARAM(HttpStatus.BAD_REQUEST),
    JPS_CLIENT_API(HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    FILE_NOT_FOUND(HttpStatus.BAD_REQUEST);

    ErrorCodeEnum(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    private final HttpStatus httpStatus;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
