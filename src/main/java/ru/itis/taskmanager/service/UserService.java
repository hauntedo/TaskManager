package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.request.EditUserDto;
import ru.itis.taskmanager.dto.response.TaskDto;
import ru.itis.taskmanager.dto.response.UserDto;

public interface UserService {

    UserDto findUserByUsername(String username);

    void updateUserByUsername(EditUserDto userDto, String username);

    Boolean hasUserByTaskId(String taskId, String username);

    Boolean isChiefUserForTask(TaskDto task, String username);
}
