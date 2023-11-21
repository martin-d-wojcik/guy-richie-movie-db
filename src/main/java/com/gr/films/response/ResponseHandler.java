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

    public static ResponseEntity<Object> createArrayInBody(String name, List<Object> actorList) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put(name, actorList);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
