package com.ecommerce.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {

    public EmailAlreadyRegisteredException(String message) {
        // Used when a registration email is already in use.
        super(message);
    }
}
