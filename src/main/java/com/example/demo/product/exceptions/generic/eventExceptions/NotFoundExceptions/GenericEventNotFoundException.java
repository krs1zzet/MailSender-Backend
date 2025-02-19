package com.example.demo.product.exceptions.generic.eventExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import com.example.demo.product.exceptions.generic.NotFoundException;

public class GenericEventNotFoundException extends NotFoundException {
    public GenericEventNotFoundException(ErrorCode errorCode, Object... args) {
        super(errorCode,args);
    }
}
