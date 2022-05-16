package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.request.CreateCommentDto;

public interface CommentService {
    String addComment(CreateCommentDto commentDto, String taskId, String username);
}
