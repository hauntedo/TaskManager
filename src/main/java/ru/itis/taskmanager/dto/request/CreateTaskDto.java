package ru.itis.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {

    private String title;
    private String description;

}
