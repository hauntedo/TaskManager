package ru.itis.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String getProfilePage() {
        return "profile";
    }


}
