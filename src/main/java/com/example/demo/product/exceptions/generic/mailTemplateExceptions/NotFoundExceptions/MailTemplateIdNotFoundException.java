package com.example.demo.product.exceptions.generic.mailTemplateExceptions.NotFoundExceptions;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;

public class MailTemplateIdNotFoundException extends GenericMailTemplateNotFoundException {
    public MailTemplateIdNotFoundException(Long mailTemplateId) {
        super(ErrorCode.MAILTEMPLATE_NOT_FOUND, mailTemplateId);
    }
}
