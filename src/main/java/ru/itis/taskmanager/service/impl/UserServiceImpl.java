package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.taskmanager.dto.response.UserResponse;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.UserService;
import ru.itis.taskmanager.util.mapper.UserMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getUserById(String id) {
        return null;
    }
}
