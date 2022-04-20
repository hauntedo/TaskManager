package ru.itis.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    String username;
    String email;
    String password;

}
