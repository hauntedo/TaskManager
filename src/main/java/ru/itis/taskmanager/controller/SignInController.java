package ru.itis.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign_in")
@RequiredArgsConstructor
public class SignInController {

    @GetMapping()
    public String getSignInPage(Authentication authentication) {
        if (authentication!=null) {
            return "redirect:/profile";
        }
        return "sign_in";
    }
}
