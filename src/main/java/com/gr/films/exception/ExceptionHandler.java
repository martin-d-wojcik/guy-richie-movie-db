package com.gr.films.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice // intercepts all exceptions globally
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotFoundException.class)     // value takes a list of exception classes
    public ResponseEntity<Object> handleMovieNotFoundException(NotFoundException movieNotFoundException) {
        // Create object of movieException and parse message and http status
        ResponseException movieException = new ResponseException(movieNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND);

        // Return ResponseEntity with movieException object
        return new ResponseEntity<>(movieException, HttpStatus.NOT_FOUND);
    }
}
