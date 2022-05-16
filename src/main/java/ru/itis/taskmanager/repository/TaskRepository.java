package ru.itis.taskmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.itis.taskmanager.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, UUID> {

    List<Task> findTasksByUsers_uuid(UUID users_uuid);
    
    List<Task> findAllByTaskStateNotLike(Task.State taskState);

}
