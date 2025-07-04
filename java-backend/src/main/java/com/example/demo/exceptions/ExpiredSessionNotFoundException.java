package com.example.demo.exceptions;

public class ExpiredSessionNotFoundException extends RuntimeException {
    public ExpiredSessionNotFoundException(String message) {
        super(message);
    }
}
