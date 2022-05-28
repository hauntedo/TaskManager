package ru.itis.taskmanager.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String msg) {
        super(msg);
    }
}
