package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.taskmanager.dto.request.CreateTaskDto;
import ru.itis.taskmanager.dto.response.TaskDto;
import ru.itis.taskmanager.exception.TaskNotFoundException;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.model.Activity;
import ru.itis.taskmanager.model.Task;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.ActivityRepository;
import ru.itis.taskmanager.repository.TaskRepository;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.TaskService;

import java.util.*;

import static ru.itis.taskmanager.dto.response.TaskDto.from;
import static ru.itis.taskmanager.model.Task.State.COMPLETED;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    //доработать
    @Override
    public List<TaskDto> findAllTasksWhereTaskStateNotCompleted() {
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
        return from(taskList);
    }

    @Transactional
    @Override
    public void addTask(CreateTaskDto createTaskDto, String username) {

        User user = userRepository.getByUserName(username);
        Task task = Task.builder()
                .title(createTaskDto.getTitle())
                .description(createTaskDto.getDescription())
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
    public TaskDto findTaskById(String id) {
        Task task = taskRepository.findById(UUID.fromString(id)).orElseThrow();
        return from(task);
    }

    @Override
    public List<TaskDto> findTasksByUsername(String username) {
        User user = userRepository.findUserByUserName(username).orElseThrow(UserNotFoundException::new);
        List<Task> tasks = taskRepository.findTasksByUsers_uuid(user.getUuid());
        return from(tasks);
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


}
