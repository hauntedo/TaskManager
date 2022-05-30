package ru.itis.taskmanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.taskmanager.validation.ValidPassword;
import ru.itis.taskmanager.validation.ValidUsername;

import javax.validation.constraints.*;

import static ru.itis.taskmanager.util.constant.Constant.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpForm {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @ValidUsername
    private String userName;

    @ValidPassword
    private String password;

    @Email(regexp = EMAIL_REGEX)
    private String email;


}
