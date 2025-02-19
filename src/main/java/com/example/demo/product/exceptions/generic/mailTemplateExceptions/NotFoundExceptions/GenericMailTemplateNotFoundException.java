package com.example.demo.product.exceptions.generic.mailTemplateExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import com.example.demo.product.exceptions.generic.NotFoundException;

public class GenericMailTemplateNotFoundException extends NotFoundException {
    public GenericMailTemplateNotFoundException(ErrorCode errorCode, Object... args) {
        super(errorCode,args);
    }
}
