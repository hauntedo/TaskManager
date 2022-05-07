package ru.itis.taskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class UsernameExistException extends RuntimeException {

    public UsernameExistException(String msg) {
        super(msg);
    }

    public UsernameExistException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameExistException(Throwable cause) {
        super(cause);
    }


}
