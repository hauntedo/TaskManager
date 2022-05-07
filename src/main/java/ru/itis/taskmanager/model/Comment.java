package ru.itis.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
@SuperBuilder
public class Comment extends AbstractEntity{

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;


}
