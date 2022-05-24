package ru.itis.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.taskmanager.model.Activity;

import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.taskmanager.dto.response.CommentResponse.from;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityResponse {

    private List<CommentResponse> commentResponseList;

    public static ActivityResponse from(Activity activity) {
        return ActivityResponse.builder()
                .commentResponseList(activity.getComments().stream().map(CommentResponse::from).collect(Collectors.toList()))
                .build();
    }


}
