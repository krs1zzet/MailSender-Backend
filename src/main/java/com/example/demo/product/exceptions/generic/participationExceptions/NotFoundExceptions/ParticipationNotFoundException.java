package com.example.demo.product.exceptions.generic.participationExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class ParticipationNotFoundException extends GenericParticipationNotFoundException {
    public ParticipationNotFoundException(Long id) {
        super(ErrorCode.PARTICIPATION_NOT_FOUND,id);
    }
}
