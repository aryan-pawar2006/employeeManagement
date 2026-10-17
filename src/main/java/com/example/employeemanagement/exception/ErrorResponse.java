package com.example.employeemanagement.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    //this  class will actually help to send response back to user and how that response should have to be we'll declare in this class:
    private LocalDateTime timeStamp;
    private Integer statusCode;
    private String message;

    public ErrorResponse(LocalDateTime timeStamp, Integer statusCode, String message) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.message = message;
    }


    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
