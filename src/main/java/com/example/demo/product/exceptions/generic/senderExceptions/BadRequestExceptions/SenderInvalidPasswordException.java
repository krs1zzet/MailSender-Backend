package com.example.demo.product.exceptions.generic.senderExceptions.BadRequestExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class SenderInvalidPasswordException extends GenericSenderBadRequestException {
    public SenderInvalidPasswordException(Long id) {
        super(ErrorCode.SENDER_INVALID_PASSWORD, id);
    }
}
