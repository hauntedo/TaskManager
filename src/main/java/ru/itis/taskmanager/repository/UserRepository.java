package ru.itis.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.taskmanager.model.UserEntity;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
