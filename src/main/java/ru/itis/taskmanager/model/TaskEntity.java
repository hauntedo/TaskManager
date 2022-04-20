package ru.itis.taskmanager.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
@Getter
@Setter
public class TaskEntity extends AbstractEntity{

    @Column(nullable = false)
    private String title;

    private String description;

    public enum State {
        OPEN, IN_PROGRESS, RESOLVED, COMPLETED
    }

    @Column(name = "task_state")
    @Enumerated(value = EnumType.STRING)
    private State taskState;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", referencedColumnName = "uuid")
    private ActivityEntity activity;

    @ManyToMany(mappedBy = "tasks")
    private Set<UserEntity> users;






}
