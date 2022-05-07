package ru.itis.taskmanager.util.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.itis.taskmanager.dto.request.TaskRequest;
import ru.itis.taskmanager.dto.response.TaskResponse;
import ru.itis.taskmanager.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toTask(TaskRequest task);

    TaskResponse toTaskResponse(Task task);


}
