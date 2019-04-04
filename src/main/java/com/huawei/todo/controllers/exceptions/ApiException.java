package com.huawei.todo.controllers.exceptions;

import lombok.Data;

@Data
public class ApiException extends Exception {

    public ApiException(ApiErrorMessage message) {

        this(message, null);
    }

    public ApiException(ApiErrorMessage message, Exception reason) {

        super(message.toString());
        System.out.println(String.format("Api Error: %s", message));
        if (reason != null) {
            reason.printStackTrace();
        }
    }
}