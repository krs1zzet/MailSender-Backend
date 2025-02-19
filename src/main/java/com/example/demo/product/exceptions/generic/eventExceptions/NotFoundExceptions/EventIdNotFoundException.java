package com.example.demo.product.exceptions.generic.eventExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class EventIdNotFoundException extends GenericEventNotFoundException {
    public EventIdNotFoundException(Long EventId) {
        super(ErrorCode.EVENT_NOT_FOUND, EventId);
    }
}
