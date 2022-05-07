package ru.itis.taskmanager.validation.impl;

import ru.itis.taskmanager.validation.ValidUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        int len = value.length();
        if (value.length() < 3) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if ((!Character.isLetterOrDigit(value.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
