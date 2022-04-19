package ru.itis.taskmanager.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "activity")
@Getter
@Setter
public class ActivityEntity extends AbstractEntity {

    @OneToMany(mappedBy = "activity")
    private List<FileEntity> files;

    @OneToMany(mappedBy = "activity")
    private List<CommentEntity> comments;

    @OneToOne(mappedBy = "activity")
    private TaskEntity task;

    @OneToOne(mappedBy = "activity")
    private UserEntity updatedBy;

}
