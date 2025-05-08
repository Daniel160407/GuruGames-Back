package com.gameroom.service.exception.user;

public class MissingRequiredDataException extends RuntimeException {
    public MissingRequiredDataException(String message) {
        super(message);
    }
}
