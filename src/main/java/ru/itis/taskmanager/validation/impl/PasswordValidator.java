package ru.itis.taskmanager.validation.impl;

import ru.itis.taskmanager.validation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static ru.itis.taskmanager.util.constant.Constant.PASSWORD_REGEX;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.matches(PASSWORD_REGEX);
    }
}
