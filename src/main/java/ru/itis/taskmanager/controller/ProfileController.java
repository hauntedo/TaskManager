package ru.itis.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.taskmanager.dto.request.EditUserDto;
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
        model.addAttribute("fullname", userDto.getFirstName() + " " + userDto.getLastName());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("aboutme", userDto.getAboutMe());
        return "profile";
    }

    @GetMapping(value = "/edit")
    public String getProfileEditPage(Model model,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        UserDto userDto = userService.findUserByUsername(userDetails.getUsername());
        model.addAttribute("firstname", userDto.getFirstName());
        model.addAttribute("lastname", userDto.getLastName());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("editProfileForm", new EditUserDto());
        return "edit_profile";
    }

    @PutMapping(value = "/edit")
    public String editProfile(@Valid EditUserDto userDto, BindingResult result, Model model,
                              @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            model.addAttribute("editProfileForm", userDto);
            return "edit_profile";
        }
        userService.updateUserByUsername(userDto , userDetails.getUsername());
        return "redirect:/profile";
    }





}
