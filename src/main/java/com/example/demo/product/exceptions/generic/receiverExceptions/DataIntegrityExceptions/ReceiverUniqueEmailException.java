package com.example.demo.product.exceptions.generic.receiverExceptions.DataIntegrityExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class ReceiverUniqueEmailException extends GenericReceiverDataIntegrityException {
    public ReceiverUniqueEmailException(String email) {
        super(ErrorCode.RECEIVER_EMAIL_NOT_UNIQUE,email);
    }
}
