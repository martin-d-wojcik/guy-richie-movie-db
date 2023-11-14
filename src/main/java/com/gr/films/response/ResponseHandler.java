package com.gr.films.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> createResponseBody(String message, HttpStatus httpStatus) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Message", message);
        responseBody.put("HttpStatus", httpStatus);

        return new ResponseEntity<>(responseBody, httpStatus);
    }
}
