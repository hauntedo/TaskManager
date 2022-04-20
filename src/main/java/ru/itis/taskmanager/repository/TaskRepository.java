package ru.itis.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.taskmanager.model.TaskEntity;

import java.util.UUID;

public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {
}
