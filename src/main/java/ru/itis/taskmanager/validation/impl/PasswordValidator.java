package ru.itis.taskmanager.validation.impl;

import ru.itis.taskmanager.validation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        int len = value.length();
        if (len < 8) {
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
