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
                              @RequestParam(name = "action", required = false) String action) {
        if (action != null) {
            switch (action) {
                case "edit":
                    if (result.hasErrors()) {
                        model.addAttribute("editUser", userDto);
                        model.addAttribute("user", userService.findUserByUsername(userDetails.getUsername()));
                        return "settings";
                    }
                    userService.updateUserByUsername(userDto , userDetails.getUsername());
                    model.addAttribute("editUser", userDto);
                    return "redirect:/profile";
                case "delete":
                    userService.deleteAccount(userDetails.getUsername());
                    return "redirect:/sign_out";
                default:
                    model.addAttribute("status", "404 Not Found");
                    model.addAttribute("message", "Request page not found");
                    return "exception_page";
            }
        } else {
            return "settings";
        }
    }
}
