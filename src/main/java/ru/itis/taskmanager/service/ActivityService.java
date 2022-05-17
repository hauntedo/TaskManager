package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.request.CreateCommentDto;
import ru.itis.taskmanager.dto.response.ActivityDto;

public interface ActivityService {

    ActivityDto findByTaskId(String taskId);

    ActivityDto convertCommentDateTime(ActivityDto activityDto);

}
