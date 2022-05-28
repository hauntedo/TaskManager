package ru.itis.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.taskmanager.dto.request.CommentRequest;
import ru.itis.taskmanager.dto.request.TaskRequest;
import ru.itis.taskmanager.dto.response.ActivityResponse;
import ru.itis.taskmanager.dto.response.TaskResponse;
import ru.itis.taskmanager.dto.response.UserResponse;
import ru.itis.taskmanager.exception.ActivityNotFoundException;
import ru.itis.taskmanager.exception.ForbiddenResourceException;
import ru.itis.taskmanager.exception.TaskNotFoundException;
import ru.itis.taskmanager.service.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ActivityService activityService;
    private final UserService userService;
    private final FileService fileService;
    private final CommentService commentService;

    @GetMapping
    public String getTasksPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserResponse user = userService.findUserByUsername(userDetails.getUsername());
        try {
            List<TaskResponse> tasks = taskService.findAllTasksWhereTaskStateNotCompleted();
            model.addAttribute("tasks", tasks);
        } catch (TaskNotFoundException e) {
            log.error(e + ": " + e.getMessage());
        }
        model.addAttribute("user", user);
        return "tasks";
    }

    @GetMapping("/add")
    public String getAddTaskPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("taskForm", new TaskRequest());
        model.addAttribute("user", userService.findUserByUsername(userDetails.getUsername()));
        return "add_task";
    }

    @PostMapping("/add")
    public String addTaskPage(@Valid TaskRequest taskRequest, BindingResult result, Model model,
                              @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            model.addAttribute("taskForm", taskRequest);
            model.addAttribute("user", userService.findUserByUsername(userDetails.getUsername()));
            return "add_task";
        }
        taskService.addTask(taskRequest, userDetails.getUsername());
        return "redirect:/tasks";
    }

    @GetMapping("/{task-id}")
    public String getTaskPage(@PathVariable("task-id") String taskId, Model model,
                              @AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam(name = "action", required = false) String action) {
       try {
           TaskResponse task = taskService.findTaskById(taskId);
           UserResponse user = userService.findUserByUsername(userDetails.getUsername());
           ActivityResponse activity = activityService.findByTaskId(taskId);

           //проверяет, создал ли пользователь эту задачу
           Boolean isChief = userService.isChiefUserForTask(task, userDetails.getUsername());

           //добавил ли пользователь эту задачу
           Boolean state = userService.hasUserByTaskId(taskId, userDetails.getUsername());
           //обновляет состояние задач, согласно параметрам запроса
           if (action != null) {
               taskService.updateState(taskId, action, userDetails.getUsername());
               state = false;
           }
           model.addAttribute("task", task);
           model.addAttribute("activity", activity);
           model.addAttribute("chief", isChief);
           model.addAttribute("state", state);
           model.addAttribute("user", user);
           if (action != null) {
               return "redirect:/tasks/" + taskId;
           } else {
               return "task";
           }
       } catch (TaskNotFoundException | IllegalArgumentException e) {
           log.error(e + ": " + e.getMessage());
           model.addAttribute("status", "404 Not Found");
           model.addAttribute("message", "Request page not found");
           return "exception_page";
       } catch (ForbiddenResourceException e) {
           log.warn(userDetails.getUsername() + " has tried to use prohibited resources");
           model.addAttribute("status", "403 Forbidden");
           model.addAttribute("message", e.getMessage());
           return "exception_page";
       }
    }

    @PostMapping("/{task-id}")
    public String addComment(@AuthenticationPrincipal UserDetails userDetails,
                                   @PathVariable("task-id") String taskId,
                                   CommentRequest commentDto,
                                   @RequestParam(value = "file", required = false) MultipartFile[] files, Model model) {
        try {
            String commentId = commentService.addComment(commentDto, taskId, userDetails.getUsername());
            fileService.upload(files, commentId, userDetails.getUsername());
            return "redirect:/tasks/" + taskId;
        } catch (ActivityNotFoundException e) {
            log.error(e + ": " + e.getMessage());
            model.addAttribute("message", "Failed to add comment");
            return "task";
        } catch (IllegalArgumentException e) {
            log.error(e  + ": " + e.getMessage());
            model.addAttribute("message", "Failed to upload file(s)");
            return "task";
        }
    }


    @GetMapping("/my")
    public String getMyTasksPage(Model model,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        UserResponse user = userService.findUserByUsername(userDetails.getUsername());
        List<TaskResponse> tasks = taskService.findTasksByUsername(userDetails.getUsername());
        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);
        return "tasks";
    }

    @GetMapping("/{task-id}/edit")
    public String getEditTaskPage(Model model,
                                  @AuthenticationPrincipal UserDetails userDetails,
                                  @PathVariable("task-id") String taskId) {
        model.addAttribute("taskForm", new TaskRequest());
        model.addAttribute("task", taskService.findTaskById(taskId));
        return "edit_task";
    }

    @PostMapping("/{task-id}/edit")
    public String editTask(@Valid TaskRequest taskRequest, BindingResult result, Model model,
                           @AuthenticationPrincipal UserDetails userDetails, @PathVariable("task-id") String taskId) {
        TaskResponse taskResponse = taskService.findTaskById(taskId);
        if (userService.isChiefUserForTask(taskResponse, userDetails.getUsername())) {
            if (result.hasErrors()) {
                model.addAttribute("taskForm", taskRequest);
                model.addAttribute("user", userService.findUserByUsername(userDetails.getUsername()));
                return "edit_task";
            }
            taskService.updateTask(taskRequest, taskId);
        }
        return "redirect:/tasks/" + taskId;
    }



}
