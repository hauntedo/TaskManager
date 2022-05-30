package ru.itis.taskmanager.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenResourceException extends RuntimeException {

    public ForbiddenResourceException(String msg) {
        super(msg);
    }

}
