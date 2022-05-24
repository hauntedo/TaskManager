package ru.itis.taskmanager.exception;

public class PasswordMismatchException extends RuntimeException {

    public PasswordMismatchException(String msg) {
        super(msg);
    }

    public PasswordMismatchException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PasswordMismatchException(Throwable cause) {
        super(cause);
    }

}
