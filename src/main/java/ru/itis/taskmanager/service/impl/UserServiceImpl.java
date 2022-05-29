package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.taskmanager.dto.request.SignUpForm;
import ru.itis.taskmanager.dto.request.UserRequest;
import ru.itis.taskmanager.dto.response.TaskResponse;
import ru.itis.taskmanager.dto.response.UserResponse;
import ru.itis.taskmanager.exception.OccupiedEmailException;
import ru.itis.taskmanager.exception.PasswordMismatchException;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.exception.OccupiedUsernameException;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.taskmanager.dto.response.UserResponse.from;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse findUserByUsername(String username) {
        User user = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return from(user);
    }

    @Override
    public void updateUserByUsername(UserRequest userDto, String username) {
        User user = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!userDto.getFirstName().isEmpty()) {
            user.setFirstName(userDto.getFirstName());
        }
        if (!userDto.getLastName().isEmpty()) {
            user.setLastName(userDto.getLastName());
        }
        if (!userDto.getAboutMe().isEmpty()) {
            user.setAboutMe(userDto.getAboutMe());
        }
        if (!userDto.getPassword().isEmpty()) {
            user.setHashPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userRepository.save(user);
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
    public Boolean isChiefUserForTask(TaskResponse task, String username) {
        User user = userRepository.findUserByUserName(task.getCreatedBy())
                .orElseThrow(UserNotFoundException::new);
        return user.getUserName().equals(username);
    }

    @Override
    public List<UserResponse> findAll() {
        Iterable<User> userIterable = userRepository.findAll();
        List<User> users = new ArrayList<>();
        userIterable.forEach(users::add);
        return from(users);
    }

    @Override
    public void registerNewAccount(SignUpForm accountForm) {

        if (userRepository.findCountByUsername(accountForm.getUserName()) > 0) {
            throw new OccupiedUsernameException(accountForm.getUserName() + " is occupied");
        } else if (userRepository.findCountByEmail(accountForm.getEmail()) > 0) {
            throw new OccupiedEmailException(accountForm.getEmail() + " is occupied");
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
    public UserResponse findUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return from(user);
    }

    @Override
    public UserResponse updateUserById(String userId, UserRequest updatingUser) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(UserNotFoundException::new);
        user.setFirstName(updatingUser.getFirstName());
        user.setLastName(updatingUser.getLastName());
        user.setAboutMe(updatingUser.getAboutMe());
        user.setHashPassword(updatingUser.getPassword());
        userRepository.save(user);
        return from(user);
    }

    @Override
    public void deleteUserById(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(UserNotFoundException::new);
        user.setState(User.State.DELETED);
        userRepository.save(user);
    }

    @Override
    public void banUserById(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(UserNotFoundException::new);
        user.setState(User.State.BANNED);
        userRepository.save(user);
    }

    @Override
    public void unBanUserById(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(UserNotFoundException::new);
        user.setState(User.State.NOT_BANNED);
        userRepository.save(user);
    }

    @Override
    public void deleteAccount(String username) {
        userRepository.deleteAccountByYourself(username);
    }

    @Override
    public void changePassword(String code, String password) {
        User user = userRepository.findByConfirmCode(UUID.fromString(code))
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setHashPassword(passwordEncoder.encode(password));
        log.info("Save user entity(password).");
        userRepository.save(user);
    }

    @Override
    public void deleteConfirmCode(String code) {
        User user = userRepository.findByConfirmCode(UUID.fromString(code))
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setConfirmCode(null);
        log.info("Save user entity(confirm code)");
        userRepository.save(user);
    }

    @Override
    public UserResponse findByConfirmCode(String code) {
        User user = userRepository.findByConfirmCode(UUID.fromString(code))
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return from(user);
    }


}
