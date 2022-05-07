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
    private List<FileInfo> files;

    @OneToMany(mappedBy = "activity")
    private List<Comment> comments;

    @OneToOne(mappedBy = "activity")
    private Task task;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "uuid")
    private User updatedBy;

}
