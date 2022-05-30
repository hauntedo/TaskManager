package ru.itis.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.taskmanager.validation.ValidPassword;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PasswordsRequest {

    @ValidPassword
    private String password;

    @ValidPassword
    private String passwordCheck;
}
