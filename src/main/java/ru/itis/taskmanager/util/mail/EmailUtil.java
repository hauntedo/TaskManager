package ru.itis.taskmanager.util.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username")
    private String from;

    public void sendMail(String to, String subject, String template) {

        final String mailText = template;

        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setText(mailText, true);
            mimeMessageHelper.setTo(to);

        };

        mailSender.send(mimeMessagePreparator);

    }

}
