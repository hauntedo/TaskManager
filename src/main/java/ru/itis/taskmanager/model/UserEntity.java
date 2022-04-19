package ru.itis.taskmanager.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class UserEntity extends AbstractEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "uuid")
    private ActivityEntity activity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "account_task",
    joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "uuid"),
    inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "uuid"))
    private List<TaskEntity> tasks;


}
