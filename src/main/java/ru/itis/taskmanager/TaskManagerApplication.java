package ru.itis.taskmanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        log.info("Before Starting application");
        SpringApplication.run(TaskManagerApplication.class, args);
    }

}
