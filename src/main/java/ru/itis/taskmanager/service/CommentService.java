package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.request.CommentRequest;

public interface CommentService {
    String addComment(CommentRequest commentDto, String taskId, String username);
}
