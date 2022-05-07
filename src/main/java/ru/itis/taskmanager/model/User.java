package ru.itis.taskmanager.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

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

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "updatedBy")
    private Activity activity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "account_task",
    joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "uuid"),
    inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "uuid"))
    private Set<Task> tasks;

    public enum Role {
        ADMIN, USER
    }

    public enum State {
        BANNED, NOT_BANNED
    }

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;


}
