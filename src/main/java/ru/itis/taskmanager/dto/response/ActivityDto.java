package ru.itis.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.taskmanager.model.Activity;
import ru.itis.taskmanager.model.Comment;
import ru.itis.taskmanager.model.FileInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.taskmanager.dto.response.CommentDto.from;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDto {

    private List<CommentDto> commentDtoList;

    public static ActivityDto from(Activity activity) {
        return ActivityDto.builder()
                .commentDtoList(activity.getComments().stream().map(CommentDto::from).collect(Collectors.toList()))
                .build();
    }


}
