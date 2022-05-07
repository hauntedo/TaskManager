package ru.itis.taskmanager.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.taskmanager.dto.request.SignUpForm;
import ru.itis.taskmanager.dto.request.UserRequest;
import ru.itis.taskmanager.dto.response.UserResponse;
import ru.itis.taskmanager.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    @Mapping(source = "hashPassword", target = "password")
    User toUser(UserRequest user);

    @Mapping(source = "hashPassword", target = "password")
    User toUser(SignUpForm user);


}
