package ru.itis.taskmanager.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
@Getter
@Setter
public class Task extends AbstractEntity{

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
    private User createdBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", referencedColumnName = "uuid")
    private Activity activity;

    @ManyToMany(mappedBy = "tasks")
    private Set<User> users;






}
