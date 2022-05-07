package ru.itis.taskmanager.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name = "file")
public class FileInfo extends AbstractEntity {

    @Column(name = "storage_file_name", nullable = false, unique = true)
    private String storageFileName;

    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    private String description;

    @Column(nullable = false)
    private Long size;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

}
