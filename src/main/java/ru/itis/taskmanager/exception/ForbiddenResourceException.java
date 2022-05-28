package ru.itis.taskmanager.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ForbiddenResourceException extends RuntimeException {

    public ForbiddenResourceException(String msg) {
        super(msg);
    }

}
