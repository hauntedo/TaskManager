package ru.itis.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.taskmanager.model.Task;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TaskDto {

    private UUID uuid;
    private String title;
    private String description;
    private String taskState;
    private String date;
    private String createdBy;

    public static TaskDto from(Task task) {
        return TaskDto.builder()
                .uuid(task.getUuid())
                .title(task.getTitle())
                .description(task.getDescription())
                .taskState(String.valueOf(task.getTaskState()))
                .date(String.valueOf(task.getCreateDate()))
                .createdBy(task.getCreatedBy().getUserName())
                .build();
    }

    public static List<TaskDto> from(List<Task> taskList) {
        return taskList.stream().map(TaskDto::from).collect(Collectors.toList());
    }

}
