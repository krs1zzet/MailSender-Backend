package com.example.demo.product.exceptions.generic;

public class MailSendingException extends RuntimeException {
    public MailSendingException(String message) {
        super(message);
    }
}
