package ru.itis.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.taskmanager.model.CommentEntity;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<CommentEntity, UUID> {
}
