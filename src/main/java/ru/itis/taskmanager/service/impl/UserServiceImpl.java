package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.taskmanager.dto.request.EditUserDto;
import ru.itis.taskmanager.dto.response.TaskDto;
import ru.itis.taskmanager.dto.response.UserDto;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.taskmanager.dto.response.UserDto.from;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto findUserByUsername(String username) {
        User user = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return from(user);
    }

    @Override
    public void updateUserByUsername(EditUserDto userDto, String username) {
        User user = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (userDto.getPassword() != null) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setAboutMe(userDto.getAboutMe());
            user.setHashPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);
        } else {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setAboutMe(userDto.getAboutMe());
            userRepository.save(user);
        }

    }

    @Override
    public Boolean hasUserByTaskId(String taskId, String username) {
        List<User> users = userRepository.findUsersByTasks_uuid(UUID.fromString(taskId))
                .orElseThrow(IllegalArgumentException::new);
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean isChiefUserForTask(TaskDto task, String username) {
        User user = userRepository.findUserByUserName(task.getCreatedBy())
                .orElseThrow(UserNotFoundException::new);
        return user.getUserName().equals(username);
    }
}
