package com.huawei.todo.controllers.exceptions;

public enum ApiErrorMessage {

    // Generic Error Messages
    ACCESS_DENIED("Access denied."),
    INVALID_OR_MISSING_DATA("Invalid or missing data."),
    UNEXPECTED_ERROR("An unexpected error occurred at server."),

    // Authentication Error Messages
    PASSWORD_TOO_WEAK("Password too weak."),
    USERNAME_ALREADY_EXISTS("This username already exists."),
    INVALID_USERNAME_OR_PASSWORD("Invalid username or password."),
    USERNAME_CANT_CONTAIN_FORBIDDEN_CHARACTERS("Username cannot contain forbidden characters."),
    PASSWORD_DOES_NOT_MATCH_REPEAT("Password does not match repeat"),

    // Misc...
    USER_NOT_FOUND("User not found.");

    String text;

    ApiErrorMessage(String text) {

        this.text = text;
    }

    @Override
    public String toString() {

        return this.text;
    }
}