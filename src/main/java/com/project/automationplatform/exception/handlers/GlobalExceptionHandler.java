package com.project.automationplatform.exception.handlers;

import com.project.automationplatform.exception.exceptions.MissingFieldValueException;
import com.project.automationplatform.exception.exceptions.ResourceAlreadyExistsException;
import com.project.automationplatform.exception.exceptions.ResourceNotFoundException;
import com.project.automationplatform.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(MissingFieldValueException exc) {
        ExceptionResponse response = new ExceptionResponse();
        response.setSummary("Missing field value.");
        response.setMessage(exc.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ResourceAlreadyExistsException exc) {
        ExceptionResponse response = new ExceptionResponse();
        response.setSummary("Resource already exists.");
        response.setMessage(exc.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ResourceNotFoundException exc) {
        ExceptionResponse response = new ExceptionResponse();
        response.setSummary("Resource is not found.");
        response.setMessage(exc.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exc) {
        ExceptionResponse response = new ExceptionResponse();
        response.setSummary("Internal Server Error");
        response.setMessage(exc.getClass().toString() + " : " + exc.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
