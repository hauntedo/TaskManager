package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.request.SignUpForm;
import ru.itis.taskmanager.dto.request.UserRequest;
import ru.itis.taskmanager.dto.response.TaskResponse;
import ru.itis.taskmanager.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse findUserByUsername(String username);

    void updateUserByUsername(UserRequest userDto, String username);

    Boolean hasUserByTaskId(String taskId, String username);

    Boolean isChiefUserForTask(TaskResponse task, String username);

    List<UserResponse> findAll();

    void registerNewAccount(SignUpForm signUpForm);

    UserResponse findUserById(UUID id);

    UserResponse updateUserById(String userId, UserRequest updateUser);

    void deleteUserById(String userId);

    void banUserById(String userId);
    void unBanUserById(String userId);

    void deleteAccount(String username);

    void changePassword(String code, String password);

    void deleteConfirmCode(String code);

    UserResponse findByConfirmCode(String code);
}
