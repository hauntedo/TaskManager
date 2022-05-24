package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.taskmanager.dto.request.CommentRequest;
import ru.itis.taskmanager.exception.ActivityNotFoundException;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.model.Activity;
import ru.itis.taskmanager.model.Comment;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.ActivityRepository;
import ru.itis.taskmanager.repository.CommentRepository;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.CommentService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public String addComment(CommentRequest commentDto, String taskId, String username) {
        User user = userRepository.findUserByUserName(username).orElseThrow(UserNotFoundException::new);
        Activity activity = activityRepository.findActivityByTask_Uuid(UUID.fromString(taskId))
                .orElseThrow(ActivityNotFoundException::new);
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .activity(activity)
                .createdBy(user)
                .build();
        activity.setUpdatedBy(user);
        activity.getComments().add(comment);
        commentRepository.save(comment);
        activityRepository.save(activity);
        return comment.getUuid().toString();
    }
}
