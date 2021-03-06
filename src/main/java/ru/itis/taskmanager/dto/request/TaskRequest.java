package ru.itis.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequest {

    @Size(max = 20)
    private String title;

    @Size(max = 250)
    private String annotation;

    @Size(max = 1024)
    private String description;

}
