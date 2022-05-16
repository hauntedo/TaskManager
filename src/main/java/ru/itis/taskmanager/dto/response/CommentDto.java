package ru.itis.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.taskmanager.model.Comment;
import ru.itis.taskmanager.model.FileInfo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentDto {

    private UUID id;
    private String content;
    private String date;
    private String createdBy;
    private List<FileInfo> files;

    public static CommentDto from(Comment comment){
        return CommentDto.builder()
                .id(comment.getUuid())
                .content(comment.getContent())
                .createdBy(comment.getCreatedBy().getUserName())
                .date(comment.getCreateDate().toString())
                .files(comment.getFiles())
                .build();
    }

    public static List<CommentDto> from(List<Comment> commentList) {
        return commentList.stream().map(CommentDto::from).collect(Collectors.toList());
    }

}
