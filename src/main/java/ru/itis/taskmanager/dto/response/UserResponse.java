package ru.itis.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.taskmanager.model.User;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String aboutMe;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .aboutMe(user.getAboutMe())
                .build();
    }

    public static List<UserResponse> from(List<User> users) {
        return users.stream().map(UserResponse::from).collect(Collectors.toList());
    }

}
