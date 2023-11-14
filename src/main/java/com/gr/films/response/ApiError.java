package com.gr.films.response;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

public class ApiError {

    private final HttpStatus status;
    private final String message;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @ResponseBody
    public JSONObject getErrorMessageBody() {
        JSONObject messageBody = new JSONObject();
        messageBody.append("Status code", this.status);
        messageBody.append("Error message", this.message);
        return messageBody;
    }
}
