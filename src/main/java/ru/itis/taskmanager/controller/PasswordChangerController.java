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
        try {
            UserResponse user = userService.findByConfirmCode(code);
            model.addAttribute("confirm", code);
            model.addAttribute("passwordForm", new PasswordsRequest());
            return "change_password";
        } catch (UserNotFoundException e) {
            log.error("User try to use the used code");
            model.addAttribute("status", "408 Request Timeout");
            model.addAttribute("message", "Outdated password change confirmation link");
            return "exception_page";
        }

    }


    @PostMapping("/change/{confirm_code}")
    public String changePassword(@Valid PasswordsRequest passwordsRequest, BindingResult result, Model model,
                                 @PathVariable("confirm_code") String code) {
        if (result.hasErrors()) {
            model.addAttribute("passwordForm", passwordsRequest);
            return "change_password";
        }
        if (passwordsRequest.getPassword().equals(passwordsRequest.getPasswordCheck())) {
            try {
                log.info("Change password.");
                userService.changePassword(code, passwordsRequest.getPassword());
                userService.deleteConfirmCode(code);
                return "success_page";
            } catch (UserNotFoundException e) {
                log.error(e + " :" + e.getMessage());
                model.addAttribute("status", "404 Not Found");
                model.addAttribute("message", "Request page not found");
                return "exception_page";
            }
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
        log.info("Run sendEmail function on controller");
        try {
            resetPasswordService.sendEmail(email);
            model.addAttribute("message", "Email has been sent to your mail");
            return "forgot_password_page";
        } catch (UserNotFoundException e) {
            model.addAttribute("message", "This email doesn't exist");
            return "forgot_password_page";
//        } catch (RuntimeException e) {
//            log.error("Send email service don't work");
//            model.addAttribute("status", "503 Service unavailable");
//            model.addAttribute("message", "Service doesn't work temporarily");
//            return "exception_page";
        }
    }
}
