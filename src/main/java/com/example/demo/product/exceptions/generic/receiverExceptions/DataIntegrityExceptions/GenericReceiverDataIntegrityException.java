package com.example.demo.product.exceptions.generic.receiverExceptions.DataIntegrityExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import com.example.demo.product.exceptions.generic.DataIntegrityException;

public class GenericReceiverDataIntegrityException extends DataIntegrityException {
    public GenericReceiverDataIntegrityException(ErrorCode errorCode, Object... args) {
        super(errorCode,args);
    }
}
