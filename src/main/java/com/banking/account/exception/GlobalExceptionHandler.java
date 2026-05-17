package com.banking.account.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountDoesNotExistException.class)
    public ResponseEntity<Map<String,Object>> handleAccountDoesNotExistException(AccountDoesNotExistException ex) {
        Map<String,Object> map = new HashMap<>();
        map.put("timestamp:", LocalDateTime.now());
        map.put("message:", ex.getMessage());
        map.put("status:", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<Map<String,Object>> handleAccountAlreadyExistException(AccountAlreadyExistsException ex) {
        Map<String,Object> map = new HashMap<>();
        map.put("timestamp:", LocalDateTime.now());
        map.put("message:", ex.getMessage());
        map.put("status:", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}
