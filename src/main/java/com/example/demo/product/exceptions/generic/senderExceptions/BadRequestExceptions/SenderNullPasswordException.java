package com.example.demo.product.exceptions.generic.senderExceptions.BadRequestExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class SenderNullPasswordException extends GenericSenderBadRequestException {
    public SenderNullPasswordException(Long id) {
        super(ErrorCode.SENDER_NULL_PASSWORD, id);
    }
}
