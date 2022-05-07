package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.response.UserResponse;

public interface UserService {

    UserResponse getUserById(String id);
}
