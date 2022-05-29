package ru.itis.taskmanager.util.mail;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.taskmanager.model.User;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreeMarkerEmailGenerator {

    public static final String CONFIRM_LINK_PREFIX = "localhost/change/";
    @Autowired
    private final FreeMarkerConfigurer configurer;

    public FreeMarkerEmailGenerator(FreeMarkerConfigurer configurer) {
        this.configurer = configurer;
    }

    public String getResetPasswordEmail(User user) throws IOException, TemplateException {

        Template template = configurer.getConfiguration().getTemplate("change_password_mail.ftlh");

        Map<String, String> attributes = new HashMap<>();
        attributes.put("action_link", CONFIRM_LINK_PREFIX + user.getConfirmCode());
        attributes.put("username", user.getUserName());

        StringWriter stringWriter = new StringWriter();

        template.process(attributes, stringWriter);
        return stringWriter.toString();

    }

}
