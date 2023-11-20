package com.gr.films.response;

import com.gr.films.model.Actor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> createResponseBody(String message, HttpStatus httpStatus) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Message", message);
        responseBody.put("HttpStatus", httpStatus);

        return new ResponseEntity<>(responseBody, httpStatus);
    }

    // Method returns an array with a name and a list of objects
    public static ResponseEntity<Object> createArrayResponseBody(String arrayName, List<Object> listOfObjects) {
        Map<String, Object> objectBody = new HashMap<>();
        objectBody.put(arrayName, listOfObjects);

        return new ResponseEntity<>(objectBody, HttpStatus.OK);
    }
}
