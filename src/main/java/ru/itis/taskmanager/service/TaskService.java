package ru.itis.taskmanager.service;

import org.springframework.transaction.annotation.Transactional;
import ru.itis.taskmanager.dto.request.CreateTaskDto;
import ru.itis.taskmanager.dto.response.TaskDto;
import ru.itis.taskmanager.model.Task;

import java.util.List;

public interface TaskService {

    List<TaskDto> convertedDateTime(List<TaskDto> taskDtoList);

    List<TaskDto> findAllTasksWhereTaskStateNotCompleted();

    void addTask(CreateTaskDto createTaskDto, String username);

    TaskDto findTaskById(String id);

    List<TaskDto> findTasksByUsername(String username);

    void addUser(String username, Task task);

    void updateState(String taskId, String action, String username);
}
