package com.example.employeemanagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice//this Anotation is actually help to handle the exception corresponding to their error:
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFound.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFound(
                ResourceNotFound ex) {
            return build(HttpStatus.NOT_FOUND, ex.getMessage());
        }

        @ExceptionHandler(DuplicateResource.class)
        public ResponseEntity<ErrorResponse> handleDuplicateResource(
                DuplicateResource ex) {
            return build(HttpStatus.CONFLICT, ex.getMessage());
        }

        @ExceptionHandler(ProjectAlreadyExist.class)
        public ResponseEntity<ErrorResponse> handleProjectAlreadyExist(
                ProjectAlreadyExist ex) {
            return build(HttpStatus.CONFLICT, ex.getMessage());
        }

        @ExceptionHandler(TaskAlreadyAssigned.class)
        public ResponseEntity<ErrorResponse> handleTaskAlreadyAssigned(
                TaskAlreadyAssigned ex) {
            return build(HttpStatus.CONFLICT, ex.getMessage());
        }

        @ExceptionHandler(InvalidRequest.class)
        public ResponseEntity<ErrorResponse> handleInvalidRequest(
                InvalidRequest ex) {
            return build(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        @ExceptionHandler(BusinessRuleViolation.class)
        public ResponseEntity<ErrorResponse> handleBusinessRuleViolation(
                BusinessRuleViolation ex) {
            return build(HttpStatus.CONFLICT, ex.getMessage());
        }


    @ExceptionHandler(MethodArgumentNotValidException.class)//suppose any method argument is not valid then call this method .
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->
                errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    //for the building the error :
    public ResponseEntity<ErrorResponse> build (HttpStatus status, String message)
    {
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),status.value(),message);//this we are calling constructor.
        return ResponseEntity.status(status).body(errorResponse);
    }

}
