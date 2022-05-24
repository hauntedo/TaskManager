package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.taskmanager.converter.DateConverter;
import ru.itis.taskmanager.dto.request.TaskRequest;
import ru.itis.taskmanager.dto.response.TaskResponse;
import ru.itis.taskmanager.exception.TaskNotFoundException;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.model.Activity;
import ru.itis.taskmanager.model.Task;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.ActivityRepository;
import ru.itis.taskmanager.repository.TaskRepository;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.TaskService;

import java.time.LocalDateTime;
import java.util.*;

import static ru.itis.taskmanager.dto.response.TaskResponse.from;
import static ru.itis.taskmanager.model.Task.State.COMPLETED;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final DateConverter dateConverter;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    @Override
    public List<TaskResponse> findAllTasksWhereTaskStateNotCompleted() {
        List<Task> taskList = new ArrayList<>();
        List<Task> helpTaskList = new ArrayList<>();
        Iterable<Task> iterable = taskRepository.findAll();
        iterable.forEach(taskList::add);
        for (Task t : taskList) {
            if (t.getTaskState().toString().equals("COMPLETED")) {
                helpTaskList.add(t);
            }
        }
        taskList.removeAll(helpTaskList);
        return convertedDateTime(from(taskList));
    }

    @Transactional
    @Override
    public void addTask(TaskRequest taskRequest, String username) {

        User user = userRepository.getByUserName(username);
        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .annotation(taskRequest.getAnnotation())
                .description(taskRequest.getDescription())
                .taskState(Task.State.OPEN)
                .createdBy(user)
                .build();
        Activity activity = Activity.builder()
                .task(task)
                .build();
        Set<User> users = new HashSet<>();
        users.add(user);
        task.setUsers(users);
        taskRepository.save(task);
        activityRepository.save(activity);
    }

    @Override
    public TaskResponse findTaskById(String id) {
        Task task = taskRepository.findById(UUID.fromString(id)).orElseThrow();
        return from(task);
    }

    @Override
    public List<TaskResponse> findTasksByUsername(String username) {
        User user = userRepository.findUserByUserName(username).orElseThrow(UserNotFoundException::new);
        List<Task> tasks = taskRepository.findTasksByUsers_uuid(user.getUuid());

        return convertedDateTime(from(tasks));
    }

    @Override
    public void addUser(String username, Task task) {
        User user = userRepository.findUserByUserName(username).orElseThrow(UserNotFoundException::new);
        task.getUsers().add(user);
        taskRepository.save(task);
    }

    @Override
    public void updateState(String taskId, String action, String username) {
        Task task = taskRepository.findById(UUID.fromString(taskId))
                .orElseThrow(TaskNotFoundException::new);
        switch (action) {
            case "add":
                addUser(username, task);
                task.setTaskState(Task.State.IN_PROGRESS);
                taskRepository.save(task);
                break;
            case "resend":
                task.setTaskState(Task.State.IN_PROGRESS);
                taskRepository.save(task);
                break;
            case "send":
                task.setTaskState(Task.State.RESOLVED);
                taskRepository.save(task);
                break;
            case "accept":
                task.setTaskState(COMPLETED);
                taskRepository.save(task);
                break;
            default:
                throw new IllegalArgumentException("Unknown command");
        }

    }

    @Override
    public void updateTask(TaskRequest taskRequest, String taskId) {
        Task task = taskRepository.findById(UUID.fromString(taskId))
                .orElseThrow(TaskNotFoundException::new);
        if (!taskRequest.getTitle().isEmpty()) {
            task.setTitle(taskRequest.getTitle());
        }
        if (!taskRequest.getAnnotation().isEmpty()) {
            task.setAnnotation(task.getAnnotation());
        }
        if (!taskRequest.getDescription().isEmpty()) {
            task.setDescription(taskRequest.getDescription());
        }
        taskRepository.save(task);
    }

    @Override
    public List<TaskResponse> convertedDateTime(List<TaskResponse> taskResponseList) {
        if (!taskResponseList.isEmpty()) {
            for (TaskResponse taskResponse : taskResponseList) {
                LocalDateTime ldt = dateConverter.convert(taskResponse.getDate());
                String newDate = Objects.requireNonNull(ldt).toString().replace("-", ".");
                taskResponse.setDate(newDate.substring(0, newDate.indexOf("T")));
            }
        }
        return taskResponseList;
    }


}
