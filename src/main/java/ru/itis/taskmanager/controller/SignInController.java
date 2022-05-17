package ru.itis.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.taskmanager.exception.UserNotFoundException;

@Controller
@RequestMapping("/sign_in")
@RequiredArgsConstructor
public class SignInController {

    @GetMapping()
    public String getSignInPage(Authentication authentication,
                                @RequestParam(value = "error", required = false) String error,
                                Model model) {
        if (authentication!=null) {
            return "redirect:/";
        }
        if (error != null) {
            model.addAttribute("message", "Username or Password incorrect");
            return "/sign_in";
        }
        return "sign_in";
    }

}
