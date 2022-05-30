package ru.itis.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
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
