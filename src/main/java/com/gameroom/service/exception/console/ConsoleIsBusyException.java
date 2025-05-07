package com.gameroom.service.exception.console;

public class ConsoleIsBusyException extends RuntimeException {
    public ConsoleIsBusyException(String message) {
        super(message);
    }
}
