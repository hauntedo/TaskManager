package ru.itis.taskmanager.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OccupiedUsernameException extends RuntimeException {

    public OccupiedUsernameException(String msg) {
        super(msg);
    }

    public OccupiedUsernameException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OccupiedUsernameException(Throwable cause) {
        super(cause);
    }


}
