package com.example.demo.product.exceptions.generic.receiverExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import com.example.demo.product.exceptions.generic.NotFoundException;

public class GenericReceiverNotFoundException extends NotFoundException {

    public GenericReceiverNotFoundException(ErrorCode errorCode,Object... args) {
        super(errorCode,args);
    }
}
