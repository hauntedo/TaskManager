package ru.itis.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    String username;
    String firstname;
    String lastname;
    String password;
    String aboutMe;
    String email;

}
