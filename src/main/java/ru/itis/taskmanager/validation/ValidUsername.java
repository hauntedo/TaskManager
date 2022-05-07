package ru.itis.taskmanager.validation;

import ru.itis.taskmanager.validation.impl.UsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername {

    String message() default "invalid username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default  {};
}
