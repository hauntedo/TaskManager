package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.taskmanager.dto.request.CreateCommentDto;
import ru.itis.taskmanager.dto.response.ActivityDto;
import ru.itis.taskmanager.exception.ActivityNotFoundException;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.model.Activity;
import ru.itis.taskmanager.model.Comment;
import ru.itis.taskmanager.model.Task;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.ActivityRepository;
import ru.itis.taskmanager.repository.CommentRepository;
import ru.itis.taskmanager.repository.TaskRepository;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.ActivityService;

import java.util.UUID;

import static ru.itis.taskmanager.dto.response.ActivityDto.from;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    @Override
    public ActivityDto findByTaskId(String taskId) {
        Activity activity = activityRepository.findActivityByTask_Uuid(UUID.fromString(taskId))
                .orElseThrow(ActivityNotFoundException::new);
        return from(activity);
    }

}
