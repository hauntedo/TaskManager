package ru.itis.taskmanager.exception;

public class OccupiedEmailException extends RuntimeException {

    public OccupiedEmailException(String msg) {
        super(msg);
    }
}
