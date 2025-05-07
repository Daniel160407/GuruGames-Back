package com.gameroom.service.exception.adminpanel;

public class InvalidEmailOrPasswordException extends RuntimeException {
    public InvalidEmailOrPasswordException(String message) {
        super(message);
    }
}
