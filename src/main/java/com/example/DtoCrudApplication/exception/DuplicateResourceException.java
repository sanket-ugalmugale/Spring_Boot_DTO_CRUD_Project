package com.example.DtoCrudApplication.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message)
    {
        super(message);
    }
}
