package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.request.EditUserDto;
import ru.itis.taskmanager.dto.response.UserDto;

public interface UserService {

    UserDto findUserByUsername(String username);

    void updateUserByUsername(EditUserDto userDto, String username);
}
