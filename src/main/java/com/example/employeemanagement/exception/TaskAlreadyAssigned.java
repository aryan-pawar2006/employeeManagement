package com.example.employeemanagement.exception;

public class TaskAlreadyAssigned extends RuntimeException{
    public TaskAlreadyAssigned(String message)
    {
        super(message);
    }
}
