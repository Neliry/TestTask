package com.example.testtask.exeption;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
