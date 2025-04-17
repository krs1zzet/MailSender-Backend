package com.example.demo.features.mailSystem.constants;

public class validationMessages {
    public static final String SENDER_NULL_PASSWORD = "Sender password cannot be empty";
    public static final String SENDER_INVALID_EMAIL = "Sender email is invalid";
    public static final String SENDER_BLANK_EMAIL = "Sender email cannot be blank";



    public static final String EVENT_NULL_PASSWORD = "Event password cannot be empty";
    public static final String EVENT_NULL_NAME = "Event name cannot be empty";
//    public static final String EVENT_NULL_DESCRIPTION = "Event description cannot be empty";

    public static final String MAIL_TEMPLATE_NULL_HEADER = "Mail template header cannot be empty";
    public static final String MAIL_TEMPLATE_NULL_BODY = "Mail template body cannot be empty";

    public static final String RECEIVER_BLANK_EMAIL = "Receiver email cannot be blank";
    public static final String RECEIVER_INVALID_EMAIL = "Receiver email is invalid";
    public static final String RECEIVER_BLANK_EVENT_ID = "Receiver event id cannot be blank";

    public static final String USER_BLANK_ID = "User id cannot be blank";
    public static final String EVENT_BLANK_ID = "Event id cannot be blank";

    private validationMessages() {} // Prevent instantiation

}
