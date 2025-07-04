package com.example.demo.exceptions;

public class InvalidEnumTypeException extends RuntimeException {
    public InvalidEnumTypeException(String message) {
        super(message);
    }
}
