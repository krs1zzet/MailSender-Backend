package com.example.demo.product.exceptions.generic.receiverExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import com.example.demo.product.exceptions.generic.eventExceptions.NotFoundExceptions.GenericEventNotFoundException;

public class ReceiverIdNotFoundException extends GenericEventNotFoundException {
    public ReceiverIdNotFoundException(Long id) {
        super(ErrorCode.RECEIVER_NOT_FOUND,id);
    }
}
