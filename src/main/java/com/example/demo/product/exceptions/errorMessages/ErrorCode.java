package com.example.demo.product.exceptions.errorMessages;

public enum ErrorCode {
    SENDER_NOT_FOUND("SND001","SENDER_NOT_FOUND with id: %s"),
    SENDER_NULL_PASSWORD("SND002","SENDER_NULL_PASSWORD with id: %s"),
    SENDER_INVALID_PASSWORD("SND003","SENDER_INVALID_PASSWORD with id: %s"),

    RECEIVER_NOT_FOUND("RCV001","RECEIVER_NOT_FOUND with id: %s"),
    RECEIVER_EMAIL_NOT_UNIQUE("RCV002","RECEIVER_EMAIL_NOT_UNIQUE with email: %s"),

    MAILTEMPLATE_NOT_FOUND("MT001","MAILTEMPLATE_NOT_FOUND with id: %s"),

    EVENT_NOT_FOUND("EV001","EVENT_NOT_FOUND with id: %s"),

    PARTICIPATION_NOT_FOUND("PT001","PARTICIPATION_NOT_FOUND with id: %s"),


    DATA_INTEGRITY_VIOLATION("DATA_INTEGRITY_VIOLATION","Data integrity violation");



    private final String code;
    private final String messageTemplate;

    ErrorCode(String code, String messageTemplate) {
        this.code = code;
        this.messageTemplate = messageTemplate;
    }

    public String getCode() {
        return code;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public String formatMessage(Object... args) {
        return String.format(messageTemplate, args);
    }

}
