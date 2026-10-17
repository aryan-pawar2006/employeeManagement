package com.example.employeemanagement.exception;

public class DuplicateResource extends RuntimeException{

    public DuplicateResource(String message)
    {
        super(message);
    }
}
