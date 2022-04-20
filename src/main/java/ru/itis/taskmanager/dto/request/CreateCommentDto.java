package ru.itis.taskmanager.dto.request;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommentDto {

    String title;
    String content;
}
