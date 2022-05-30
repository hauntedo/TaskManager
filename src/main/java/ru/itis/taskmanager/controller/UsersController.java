package ru.itis.taskmanager.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.taskmanager.dto.request.SignUpForm;
import ru.itis.taskmanager.dto.request.UserRequest;
import ru.itis.taskmanager.dto.response.UserResponse;
import ru.itis.taskmanager.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UsersController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody SignUpForm signUpForm) {
        userService.registerNewAccount(signUpForm);
        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> banUser(@PathVariable("user-id") String userId) {
        userService.banUserById(userId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @PutMapping("/{user-id}")
    public ResponseEntity<UserResponse> unBanUser(@RequestBody UserRequest updateUser,
                                                   @PathVariable("user-id") String userId) {
        userService.unBanUserById(userId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

}
