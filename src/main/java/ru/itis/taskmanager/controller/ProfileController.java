package ru.itis.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.taskmanager.dto.request.UserRequest;
import ru.itis.taskmanager.dto.response.UserResponse;
import ru.itis.taskmanager.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String getProfilePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserResponse userResponse = userService.findUserByUsername(userDetails.getUsername());
        model.addAttribute("user", userResponse);
        return "profile";
    }

}
