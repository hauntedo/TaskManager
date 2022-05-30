package ru.itis.taskmanager.validation;

import ru.itis.taskmanager.validation.impl.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Password must contain digits, upper case alphabet, lower case alphabet, special characters, and 8 characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default  {};



}
