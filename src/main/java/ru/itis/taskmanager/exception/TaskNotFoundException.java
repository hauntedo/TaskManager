package ru.itis.taskmanager.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String msg) {
        super(msg);
    }
}
