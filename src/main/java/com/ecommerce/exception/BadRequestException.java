package com.ecommerce.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        // 400-level input/validation error message.
        super(message);
    }
}
