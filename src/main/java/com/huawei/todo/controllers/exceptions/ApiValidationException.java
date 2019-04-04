package com.huawei.todo.controllers.exceptions;

import lombok.Data;
import org.springframework.validation.Errors;

@Data
public class ApiValidationException extends ApiException {

    Errors errors;

    public ApiValidationException(Errors errors) {

        super(ApiErrorMessage.INVALID_OR_MISSING_DATA);

        this.errors = errors;
    }
}