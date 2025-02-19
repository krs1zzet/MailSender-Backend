package com.example.demo.product.exceptions.generic;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class NotFoundException extends ApiException {
    public NotFoundException(ErrorCode errorCode, Object... args) {
        super(errorCode,args);
    }
}
