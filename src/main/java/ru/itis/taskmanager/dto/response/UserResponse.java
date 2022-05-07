package ru.itis.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String aboutMe;

}
