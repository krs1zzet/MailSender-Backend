package com.example.demo.product.exceptions.generic;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class DataIntegrityException extends ApiException {
    public DataIntegrityException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
