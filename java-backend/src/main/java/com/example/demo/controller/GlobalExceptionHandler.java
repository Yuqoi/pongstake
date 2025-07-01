package com.example.demo.controller;

import com.example.demo.exceptions.InvalidEnumTypeException;
import com.example.demo.exceptions.InvalidFieldsException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFieldsException.class)
    public ResponseEntity<?> handleInvalidFieldsException(InvalidFieldsException e){
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

    // Wrong enum
    @ExceptionHandler(InvalidEnumTypeException.class)
    public ResponseEntity<?> handleMisspeledEnumException(InvalidEnumTypeException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

}
