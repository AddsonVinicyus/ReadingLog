package com.adx.ReadingLog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<RestErrorMessage> userAlreadyExistsException(UserAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage())
        );
    }

}
