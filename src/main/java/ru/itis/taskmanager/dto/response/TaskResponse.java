package ru.itis.taskmanager.dto.response;

import lombok.*;
import ru.itis.taskmanager.model.Task;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TaskResponse {

    private UUID uuid;
    private String title;
    private String description;
    private String taskState;
    private String date;
    private String createdBy;
    private String annotation;

    public static TaskResponse from(Task task) {
        return TaskResponse.builder()
                .uuid(task.getUuid())
                .title(task.getTitle())
                .description(task.getDescription())
                .taskState(String.valueOf(task.getTaskState()))
                .date(String.valueOf(task.getCreateDate()))
                .annotation(task.getAnnotation())
                .createdBy(task.getCreatedBy().getUserName())
                .build();
    }

    public static List<TaskResponse> from(List<Task> taskList) {
        return taskList.stream().map(TaskResponse::from).collect(Collectors.toList());
    }

}
