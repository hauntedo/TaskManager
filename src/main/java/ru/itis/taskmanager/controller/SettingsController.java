package ru.itis.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.taskmanager.dto.request.UserRequest;
import ru.itis.taskmanager.dto.response.UserResponse;
import ru.itis.taskmanager.exception.TaskNotFoundException;
import ru.itis.taskmanager.exception.UnknownActionException;
import ru.itis.taskmanager.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/settings")
public class SettingsController {
    private final UserService userService;

    @GetMapping
    public String getSettings(Model model,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        UserResponse userResponse = userService.findUserByUsername(userDetails.getUsername());
        model.addAttribute("user", userResponse);
        model.addAttribute("editUser", new UserRequest());
        return "settings";
    }

    @PostMapping
    public String editProfile(@Valid UserRequest userDto, BindingResult result, Model model,
                              @AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam(name = "action", required = false) String action,
                              @RequestParam(name = "passwordCheck", required = false) String passwordCheck) {
        if (action != null) {
            switch (action) {
                case "edit":
                    if (result.hasErrors()) {
                        model.addAttribute("editUser", userDto);
                        model.addAttribute("user", userService.findUserByUsername(userDetails.getUsername()));
                        return "settings";
                    }
                    if (userDto.getPassword().equals(passwordCheck)) {
                        userService.updateUserByUsername(userDto , userDetails.getUsername());
                        model.addAttribute("editUser", userDto);
                        return "redirect:/profile";
                    } else {
                        model.addAttribute("message", "Password is mismatch");
                        model.addAttribute("editUser", userDto);
                        model.addAttribute("user", userService.findUserByUsername(userDetails.getUsername()));
                        return "settings";
                    }
                case "delete":
                    userService.deleteAccount(userDetails.getUsername());
                    return "redirect:/sign_out";
                default:
                    log.error("");
                    throw new UnknownActionException();
            }
        } else {
            return "settings";
        }
    }
}
