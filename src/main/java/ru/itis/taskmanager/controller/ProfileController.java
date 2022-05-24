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

    @GetMapping(value = "/edit")
    public String getProfileEditPage(Model model,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        UserResponse userResponse = userService.findUserByUsername(userDetails.getUsername());
        model.addAttribute("user", userResponse);
        model.addAttribute("editUser", new UserRequest());
        return "edit_profile";
    }

    @PostMapping(value = "/edit")
    public String editProfile(@Valid UserRequest userDto, BindingResult result, Model model,
                              @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            model.addAttribute("editUser", userDto);
            model.addAttribute("user", userService.findUserByUsername(userDetails.getUsername()));
            return "profile";
        }
        userService.updateUserByUsername(userDto , userDetails.getUsername());
        model.addAttribute("editUser", userDto);
        return "redirect:/profile";
    }





}
