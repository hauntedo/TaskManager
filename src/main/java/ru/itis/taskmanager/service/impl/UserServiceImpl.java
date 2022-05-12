package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.taskmanager.dto.request.EditUserDto;
import ru.itis.taskmanager.dto.response.UserDto;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.UserService;

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
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAboutMe(userDto.getAboutMe());
        user.setHashPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }
}
