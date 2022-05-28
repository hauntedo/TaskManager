package ru.itis.taskmanager.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.taskmanager.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    User getByUserName(String userName);
    Optional<User> findUserByUserName(String username);

    Optional<List<User>> findUsersByTasks_uuid(UUID tasks_uuid);

    @Query(value = "select count(email) from account where email = :email", nativeQuery = true)
    Integer findCountByEmail(@Param("email") String email);

    @Query(value = "select count(username) from account where username = :username", nativeQuery = true)
    Integer findCountByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("update User u set u.state = 'DELETED' where u.userName = :username")
    void deleteAccountByYourself(String username);
}
