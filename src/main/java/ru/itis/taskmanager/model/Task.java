package ru.itis.taskmanager.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
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
        OPEN, IN_PROGRESS, RESOLVED, COMPLETED, DELETED
    }

    @Column(name = "task_state")
    @Enumerated(value = EnumType.STRING)
    private State taskState;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "account_task",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "uuid"))
    private Set<User> users = new HashSet<>();








}
