package com.gr.films.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice // intercepts all exceptions globally
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotFoundException.class)     // value takes a list of exception classes
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException) {
        // Create object of movieException and parse message and http status
        ResponseException exception = new ResponseException(notFoundException.getMessage(),
                HttpStatus.NOT_FOUND);

        // Return ResponseEntity with movieException object
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException) {
        ResponseException exception = new ResponseException(badRequestException.getMessage(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
