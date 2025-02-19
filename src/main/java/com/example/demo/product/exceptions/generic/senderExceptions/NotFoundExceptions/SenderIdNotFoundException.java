package com.example.demo.product.exceptions.generic.senderExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class SenderIdNotFoundException extends GenericSenderNotFoundException {
    public SenderIdNotFoundException(Long senderId) {
        super(ErrorCode.SENDER_NOT_FOUND, senderId);
    }
}
