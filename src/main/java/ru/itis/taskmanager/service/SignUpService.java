package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.request.SignUpForm;

public interface SignUpService {
    void registerNewAccount(SignUpForm accountForm);
    boolean isUsernameExist(String username);
}