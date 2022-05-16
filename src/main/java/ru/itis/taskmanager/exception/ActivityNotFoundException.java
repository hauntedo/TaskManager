package ru.itis.taskmanager.exception;

public class ActivityNotFoundException extends RuntimeException {

    public ActivityNotFoundException(String msg) {
        super(msg);
    }

    public ActivityNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ActivityNotFoundException(Throwable cause) {
        super(cause);
    }

    public ActivityNotFoundException() {
        super();
    }
}
