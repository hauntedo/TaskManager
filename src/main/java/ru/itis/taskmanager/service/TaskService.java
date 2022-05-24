package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.request.TaskRequest;
import ru.itis.taskmanager.dto.response.TaskResponse;
import ru.itis.taskmanager.model.Task;

import java.util.List;

public interface TaskService {

    List<TaskResponse> convertedDateTime(List<TaskResponse> taskResponseList);

    List<TaskResponse> findAllTasksWhereTaskStateNotCompleted();

    void addTask(TaskRequest taskRequest, String username);

    TaskResponse findTaskById(String id);

    List<TaskResponse> findTasksByUsername(String username);

    void addUser(String username, Task task);

    void updateState(String taskId, String action, String username);

    void updateTask(TaskRequest taskRequest, String taskId);
}
