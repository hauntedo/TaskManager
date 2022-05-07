package ru.itis.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.taskmanager.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUserName(String username);
}
