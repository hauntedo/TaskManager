package ru.itis.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class User extends AbstractEntity {

    @Column(name = "username", nullable = false, unique = true, updatable = false)
    private String userName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "confirm_code")
    private UUID confirmCode;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private Set<Task> tasks = new HashSet<>();

    public enum Role {
        ADMIN, USER
    }

    public enum State {
        BANNED, NOT_BANNED, DELETED
    }

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;


}
