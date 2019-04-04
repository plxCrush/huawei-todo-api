package com.huawei.todo.controllers.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(ApiErrorMessage message) {

        super(message.toString());
    }

    public AuthenticationException(String message) {

        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {

        super(message, cause);
    }
}