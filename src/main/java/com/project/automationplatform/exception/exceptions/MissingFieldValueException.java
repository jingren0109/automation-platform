package com.project.automationplatform.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingFieldValueException extends RuntimeException {

    public MissingFieldValueException(String message) {
        super(message);
    }
}
