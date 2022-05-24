package ru.itis.taskmanager.service;

import ru.itis.taskmanager.dto.response.ActivityResponse;

public interface ActivityService {

    ActivityResponse findByTaskId(String taskId);

    ActivityResponse convertCommentDateTime(ActivityResponse activityResponse);

}
