package com.example.demo.product.exceptions.generic.receiverExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import java.util.List;

public class ReceiverListIdNotFoundException extends GenericReceiverNotFoundException {
    public ReceiverListIdNotFoundException(List<Long> receiverIds) {
        super(ErrorCode.RECEIVER_NOT_FOUND, String.join(", ", receiverIds.stream().map(String::valueOf).toList()));
    }
}
