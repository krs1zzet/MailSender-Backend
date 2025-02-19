package com.example.demo.product.exceptions.generic;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class BadRequestException extends ApiException {
    public BadRequestException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
