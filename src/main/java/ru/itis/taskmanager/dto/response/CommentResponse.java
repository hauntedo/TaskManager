package ru.itis.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private UUID id;
    private String title;
    private String content;
    private String date;
    private String createdBy;

}
