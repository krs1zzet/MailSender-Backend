package com.example.demo.product.exceptions.generic.senderExceptions.BadRequestExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import com.example.demo.product.exceptions.generic.BadRequestException;

public class GenericSenderBadRequestException extends BadRequestException {
    public GenericSenderBadRequestException(ErrorCode errorCode, Object... args) {
        super(errorCode,args);
    }
}
