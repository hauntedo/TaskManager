package ru.itis.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommentDto {

    @NotBlank
    @Size(max = 20)
    String title;

    @NotBlank
    @Size(max = 512)
    String content;

}
