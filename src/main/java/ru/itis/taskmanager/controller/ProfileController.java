package ru.itis.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.taskmanager.dto.request.EditUserDto;
import ru.itis.taskmanager.dto.request.SignUpForm;
import ru.itis.taskmanager.dto.response.UserDto;
import ru.itis.taskmanager.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String getProfilePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserDto userDto = userService.findUserByUsername(userDetails.getUsername());
        model.addAttribute("user", userDto);
        return "profile";
    }

    @GetMapping(value = "/edit")
    public String getProfileEditPage(Model model,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        UserDto userDto = userService.findUserByUsername(userDetails.getUsername());
        model.addAttribute("user", userDto);
        model.addAttribute("editUserDto", new EditUserDto());
        return "edit_profile";
    }

    @PostMapping(value = "/edit")
    public String editProfile(EditUserDto userDto, Model model,
                              @AuthenticationPrincipal UserDetails userDetails) {
        userService.updateUserByUsername(userDto , userDetails.getUsername());
        model.addAttribute("editUserDto", userDto);
        return "redirect:/profile";
    }





}
