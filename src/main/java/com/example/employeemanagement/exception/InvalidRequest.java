package com.example.employeemanagement.exception;

public class InvalidRequest extends RuntimeException{

    public InvalidRequest(String message)
    {
        super(message);
    }
}
