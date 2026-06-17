package com.processmind.common.constants;

public final class ValidationConstants {

    private ValidationConstants() {}

    // User validation
    public static final int MIN_USERNAME_LENGTH = 3;
    public static final int MAX_USERNAME_LENGTH = 50;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 100;
    public static final int MAX_EMAIL_LENGTH = 100;

    // Organization validation
    public static final int MIN_ORG_NAME_LENGTH = 2;
    public static final int MAX_ORG_NAME_LENGTH = 100;

    // SOP validation
    public static final int MIN_SOP_TITLE_LENGTH = 3;
    public static final int MAX_SOP_TITLE_LENGTH = 200;

    // Message validation
    public static final int MAX_MESSAGE_LENGTH = 1000;

    // Regular expressions
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String PHONE_PATTERN = "^\\+?[1-9][0-9]{7,14}$";
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,50}$";
}