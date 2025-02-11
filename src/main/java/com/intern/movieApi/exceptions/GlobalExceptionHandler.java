package com.intern.movieApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exeption)
    {
        return new ResponseEntity<>(exeption.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleGlobalException(Exception exeption){
        return new ResponseEntity<>(exeption.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
