package com.gr.films.exception;

import org.springframework.http.HttpStatus;

public class ResponseException {

    private final String message;
    private final HttpStatus httpStatus;

    public ResponseException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
