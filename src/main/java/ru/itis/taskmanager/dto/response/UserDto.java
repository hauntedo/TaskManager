package ru.itis.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String aboutMe;
}
