package ru.itis.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.taskmanager.model.Comment;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {
}
