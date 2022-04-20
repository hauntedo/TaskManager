package ru.itis.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private UUID id;
    private String title;
    private String description;
    private String taskState;
    private String createdBy;

}
