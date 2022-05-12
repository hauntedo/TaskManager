package ru.itis.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.taskmanager.model.Task;

import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, UUID> {

}
