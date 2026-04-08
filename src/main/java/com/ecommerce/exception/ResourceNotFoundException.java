package com.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        // Thrown when a requested entity does not exist.
        super(message);
    }
}
