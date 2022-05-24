package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.taskmanager.converter.DateConverter;
import ru.itis.taskmanager.dto.response.ActivityResponse;
import ru.itis.taskmanager.dto.response.CommentResponse;
import ru.itis.taskmanager.exception.ActivityNotFoundException;
import ru.itis.taskmanager.model.Activity;
import ru.itis.taskmanager.repository.ActivityRepository;
import ru.itis.taskmanager.repository.CommentRepository;
import ru.itis.taskmanager.repository.TaskRepository;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.ActivityService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static ru.itis.taskmanager.dto.response.ActivityResponse.from;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final DateConverter dateConverter;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    @Override
    public ActivityResponse findByTaskId(String taskId) {
        Activity activity = activityRepository.findActivityByTask_Uuid(UUID.fromString(taskId))
                .orElseThrow(ActivityNotFoundException::new);

        return convertCommentDateTime(from(activity));
    }

    @Override
    public ActivityResponse convertCommentDateTime(ActivityResponse activityResponse) {
        List<CommentResponse> commentResponseList = activityResponse.getCommentResponseList();
        if (!commentResponseList.isEmpty()) {
            for (CommentResponse comment : commentResponseList) {
                LocalDateTime ldt = dateConverter.convert(comment.getDate());
                String newDate = Objects.requireNonNull(ldt).toString()
                        .replace("T", " ");
                comment.setDate(newDate.substring(0, newDate.indexOf(".")));
            }
            activityResponse.setCommentResponseList(commentResponseList);
        }
        return activityResponse;
    }


}
