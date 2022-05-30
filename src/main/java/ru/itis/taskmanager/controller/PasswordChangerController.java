package ru.itis.taskmanager.controller;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.taskmanager.dto.request.PasswordsRequest;
import ru.itis.taskmanager.dto.response.UserResponse;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.service.ResetPasswordService;
import ru.itis.taskmanager.service.UserService;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PasswordChangerController {

    private final UserService userService;
    private final ResetPasswordService resetPasswordService;

    //TODO доделать до различного кода доступа
    @GetMapping("/change/{confirm_code}")
    public String getChangePasswordPage(@PathVariable("confirm_code") String code, Model model) {
        UserResponse user = userService.findByConfirmCode(code);
        model.addAttribute("confirm", code);
        model.addAttribute("passwordForm", new PasswordsRequest());
        return "change_password";
    }


    @PostMapping("/change/{confirm_code}")
    public String changePassword(@Valid PasswordsRequest passwordsRequest, BindingResult result, Model model,
                                 @PathVariable("confirm_code") String code) {
        if (result.hasErrors()) {
            model.addAttribute("passwordForm", passwordsRequest);
            return "change_password";
        }
        if (passwordsRequest.getPassword().equals(passwordsRequest.getPasswordCheck())) {
            userService.changePassword(code, passwordsRequest.getPassword());
            userService.deleteConfirmCode(code);
            return "success_page";
        } else {
            model.addAttribute("message", "Password mismatch");
            return "redirect:/change/" + code;
        }
    }

    @GetMapping("/forgot_password")
    public String getForgotPassword(Model model) {
        return "forgot_password_page";
    }

    @PostMapping("/forgot_password")
    public String sendEmail(@RequestParam("email") String email, Model model) {
        resetPasswordService.sendEmail(email);
        model.addAttribute("message", "Email has been sent to your mail");
        return "forgot_password_page";
    }
}
