package ru.itis.taskmanager.config;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itis.taskmanager.converter.DateConverter;

@Configuration
@ComponentScan("ru.itis.taskmanager")
public class RootConfig {

    @Bean
    public DateConverter dateConverter() {
        return new DateConverter();
    }

}
