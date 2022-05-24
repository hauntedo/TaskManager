package ru.itis.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.taskmanager.dto.request.SignUpForm;
import ru.itis.taskmanager.exception.OccupiedEmailException;
import ru.itis.taskmanager.exception.PasswordMismatchException;
import ru.itis.taskmanager.exception.OccupiedUsernameException;
import ru.itis.taskmanager.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sign_up")
public class SignUpController {

    private final UserService userService;

    @GetMapping
    public String getSignUpPage(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/";
        }
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign_up";
    }

    @PostMapping
    public String signUp(@Valid SignUpForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("signUpForm", form);
            return "sign_up";
        }
        if (form.getPassword().equals(form.getCheckPassword())) {
            try {
                userService.registerNewAccount(form);
                return "redirect:/sign_in";
            } catch (OccupiedUsernameException | OccupiedEmailException e) {
                model.addAttribute("message", e.getMessage());
                return "sign_up";
            }
        } else {
            model.addAttribute("message", "Password is mismatch");
            return "sign_up";
        }
    }


}
