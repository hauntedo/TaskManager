package ru.itis.taskmanager.service.impl;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.ResetPasswordService;
import ru.itis.taskmanager.util.mail.EmailUtil;
import ru.itis.taskmanager.util.mail.FreeMarkerEmailGenerator;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final UserRepository userRepository;

    private final EmailUtil emailUtil;

    private final FreeMarkerConfigurer freeMarkerConfigurer;


    @Override
    public void sendEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        UUID code = UUID.randomUUID();
        user.setConfirmCode(code);
        userRepository.save(user);
        log.info("Create FreeMarkerEmailGenerator");
        FreeMarkerEmailGenerator freeMarkerEmailGenerator = new FreeMarkerEmailGenerator(freeMarkerConfigurer);

        try {
            String template = freeMarkerEmailGenerator.getResetPasswordEmail(user);
            emailUtil.sendMail(user.getEmail(), "Reset password", template);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
