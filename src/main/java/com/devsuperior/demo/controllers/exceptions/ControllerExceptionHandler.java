package com.devsuperior.demo.controllers.exceptions;

import com.devsuperior.demo.services.exceptions.DatabaseException;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError errors = new StandardError();
        errors.setTimestamp(Instant.now());
        errors.setStatus(status.value());
        errors.setError("Resource not found");
        errors.setMessage(e.getMessage());
        errors.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(errors);

    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError errors = new StandardError();
        errors.setTimestamp(Instant.now());
        errors.setStatus(status.value());
        errors.setError("Database exception");
        errors.setMessage(e.getMessage());
        errors.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(errors);
    }
}
