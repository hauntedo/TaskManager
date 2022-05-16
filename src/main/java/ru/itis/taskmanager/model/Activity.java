package ru.itis.taskmanager.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "activity")
@Getter
@Setter
public class Activity extends AbstractEntity {

    @OneToMany(mappedBy = "activity")
    private List<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "uuid")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User updatedBy;

}
