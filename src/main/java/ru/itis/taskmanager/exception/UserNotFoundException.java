package ru.itis.taskmanager.exception;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}

