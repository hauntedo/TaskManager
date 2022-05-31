package ru.itis.taskmanager.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.taskmanager.validation.ValidPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequest {

    @Size(max = 32)
    private String firstName;

    @Size(max = 32)
    private String lastName;

    private String password;

    @Size(max = 1024)
    private String aboutMe;

}
