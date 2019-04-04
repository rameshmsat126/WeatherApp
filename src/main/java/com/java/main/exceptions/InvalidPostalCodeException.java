package com.java.main.exceptions;

public class InvalidPostalCodeException extends RuntimeException {

    private static final long serialVersionUID = 8084360582778818579L;

    public InvalidPostalCodeException() {
        super();
    }

    public InvalidPostalCodeException(String message) {
        super(message);
    }

    public InvalidPostalCodeException(String message, Throwable ex) {
        super(message, ex);
    }
}