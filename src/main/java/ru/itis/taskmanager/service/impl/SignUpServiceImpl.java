package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.taskmanager.dto.request.SignUpForm;
import ru.itis.taskmanager.exception.UsernameExistException;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.SignUpService;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerNewAccount(SignUpForm accountForm) {
        if (isUsernameExist(accountForm.getUserName())) {

            throw new UsernameExistException("Username is exist: " + accountForm.getUserName());

        } else {
            User newUser = User.builder()
                    .firstName(accountForm.getFirstName())
                    .lastName(accountForm.getLastName())
                    .userName(accountForm.getUserName())
                    .email(accountForm.getEmail())
                    .hashPassword(passwordEncoder.encode(accountForm.getPassword()))
                    .role(User.Role.USER)
                    .state(User.State.NOT_BANNED)
                    .build();

            userRepository.save(newUser);

        }

    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.findUserByUserName(username).isPresent();
    }
}
