package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.taskmanager.converter.DateConverter;
import ru.itis.taskmanager.dto.request.TaskRequest;
import ru.itis.taskmanager.dto.response.TaskResponse;
import ru.itis.taskmanager.exception.ForbiddenResourceException;
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
import static ru.itis.taskmanager.model.Task.State.*;

@Slf4j
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
        Iterable<Task> iterable = taskRepository.findAllNotCompletedTasks()
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        iterable.forEach(taskList::add);
        return convertedDateTime(from(taskList));
    }

    @Transactional
    @Override
    public void addTask(TaskRequest taskRequest, String username) {
        User user = userRepository.findUserByUserName(username)
                .orElseThrow(UserNotFoundException::new);
        log.info("Try to create Task entity");
        if (taskRequest.getAnnotation().length() < 250) {
            String newAnnotation = taskRequest.getAnnotation();
            int diff = 250 - newAnnotation.length();
            taskRequest.setAnnotation(newAnnotation + " ".repeat(diff));
        }
        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .annotation(taskRequest.getAnnotation())
                .description(taskRequest.getDescription())
                .taskState(Task.State.OPEN)
                .createdBy(user)
                .build();
        log.info("Try to add the Task for the Activity");
        Activity activity = Activity.builder()
                .task(task)
                .build();
        Set<User> users = new HashSet<>();
        users.add(user);
        task.setUsers(users);
        log.info("Save Task entity");
        taskRepository.save(task);
        log.info("Save Activity entity");
        activityRepository.save(activity);
    }

    @Override
    public TaskResponse findTaskById(String id) {
        log.info("Send query to database.");
        Task task = taskRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new TaskNotFoundException("Task not found")
        );
        return from(task);
    }

    @Override
    public List<TaskResponse> findTasksByUsername(String username) {
        log.info("Send query to database.");
        List<Task> tasks = taskRepository.findTasksByUsername(username)
                .orElseThrow(() -> new TaskNotFoundException("Tasks not found"));
        return convertedDateTime(from(tasks));
    }

    @Override
    public void addUser(String username, Task task) {
        User user = userRepository.findUserByUserName(username).orElseThrow(UserNotFoundException::new);
        log.info("Try add user for the task.");
        task.getUsers().add(user);
        log.info("Save task entity.");
        taskRepository.save(task);
    }

    @Override
    public void updateState(String taskId, String action, String username) {
        Task task = taskRepository.findById(UUID.fromString(taskId))
                .orElseThrow(TaskNotFoundException::new);
        boolean isChief = task.getCreatedBy().getUserName().equals(username);
        switch (action) {
            case "add":
                addUser(username, task);
                task.setTaskState(Task.State.IN_PROGRESS);
                taskRepository.save(task);
                break;
            case "resend":
                if (isChief) {
                    task.setTaskState(Task.State.IN_PROGRESS);
                    taskRepository.save(task);
                    break;
                } else {
                    throw new ForbiddenResourceException("You dont have access");
                }
            case "send":
                task.setTaskState(Task.State.RESOLVED);
                taskRepository.save(task);
                break;
            case "accept":
                if (isChief) {
                    task.setTaskState(COMPLETED);
                    taskRepository.save(task);
                    break;
                } else {
                    throw new ForbiddenResourceException("You dont have access");
                }
            default:
                throw new TaskNotFoundException();
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
        log.info("update entity");
        taskRepository.save(task);
    }

    @Override
    public Integer takeCountOfUserForTask(String taskId) {
        return taskRepository.takeCountOfUserForTask(UUID.randomUUID());
    }

    @Override
    public List<TaskResponse> findTasksByState(String section) {
        switch (section) {
            case "OPEN":
                return convertedDateTime(from(taskRepository.findTasksByTaskState(OPEN)));
            case "IN_PROGRESS":
                return convertedDateTime(from(taskRepository.findTasksByTaskState(IN_PROGRESS)));
            case "RESOLVED":
                return convertedDateTime(from(taskRepository.findTasksByTaskState(RESOLVED)));
            case "COMPLETED":
                return convertedDateTime(from(taskRepository.findTasksByTaskState(COMPLETED)));
            default:
                throw new TaskNotFoundException();
        }
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
