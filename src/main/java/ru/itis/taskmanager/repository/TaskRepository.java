package ru.itis.taskmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.itis.taskmanager.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, UUID> {
    @Query(nativeQuery = true, value = "select * from task " +
            "where task_state != 'COMPLETED'")
    Optional<List<Task>> findAllNotCompletedTasks();

    @Query(nativeQuery = true, value = "select * from task where created_by = " +
            "(select uuid from account where username = :username)")
    Optional<List<Task>> findTasksByUsername(@Param("username") String username);

}
