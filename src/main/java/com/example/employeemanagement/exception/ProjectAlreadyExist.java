package com.example.employeemanagement.exception;

public class ProjectAlreadyExist extends RuntimeException{
    public ProjectAlreadyExist(String message)
    {
        super(message);
    }
}
