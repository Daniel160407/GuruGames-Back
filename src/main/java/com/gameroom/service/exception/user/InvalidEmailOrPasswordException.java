package com.gameroom.service.exception.user;

public class InvalidEmailOrPasswordException extends RuntimeException {
    public InvalidEmailOrPasswordException(String message) {
        super(message);
    }
}
