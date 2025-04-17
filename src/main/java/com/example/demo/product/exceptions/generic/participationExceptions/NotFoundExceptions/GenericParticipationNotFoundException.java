package com.example.demo.product.exceptions.generic.participationExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import com.example.demo.product.exceptions.generic.NotFoundException;

public class GenericParticipationNotFoundException extends NotFoundException {
    public GenericParticipationNotFoundException(ErrorCode errorCode, Object... args) {
        super(errorCode,args);
    }

}
