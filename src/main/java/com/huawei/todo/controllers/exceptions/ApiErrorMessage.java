package com.huawei.todo.controllers.exceptions;

public enum ApiErrorMessage {

    // Generic Error Messages
    ACCESS_DENIED("Access denied."),
    INVALID_OR_MISSING_DATA("Invalid or missing data."),
    UNEXPECTED_ERROR("An unexpected error occurred at server."),

    // Authentication Error Messages
    USERNAME_ALREADY_EXISTS("This username already exists."),
    INVALID_USERNAME_OR_PASSWORD("Invalid username or password."),
    PASSWORD_DOES_NOT_MATCH_REPEAT("Password does not match repeat"),

    // Misc...
    TODOLIST_NOT_FOUND("Todo list not found."),
    TODOITEM_NOT_FOUND("Todo item not found."),
    USER_NOT_FOUND("User not found."),
    TODOITEM_HAVE_INCOMPLETED_DEPENDENCIES("Todo item have incomplete dependencies."),
    ITEMS_ARE_NOT_IN_THE_SAME_LIST("To-do items are not in the same list."),
    NOT_YOUR_TODOLIST("Not authorized");

    String text;

    ApiErrorMessage(String text) {

        this.text = text;
    }

    @Override
    public String toString() {

        return this.text;
    }
}