package com.example.demo.product.exceptions.generic.senderExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import com.example.demo.product.exceptions.generic.NotFoundException;
import lombok.Getter;

@Getter
public class GenericSenderNotFoundException extends NotFoundException {
    public GenericSenderNotFoundException(ErrorCode errorCode, Object... args) {
        super(errorCode,args);
    }
}
