package ru.itis.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotBlank
    String username;

    @NotBlank
    String password;

}
