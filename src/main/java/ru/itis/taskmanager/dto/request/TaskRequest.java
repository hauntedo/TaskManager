package ru.itis.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    @NotBlank
    @Size(max = 20)
    private String title;

    @Size(max = 1024)
    private String description;

}