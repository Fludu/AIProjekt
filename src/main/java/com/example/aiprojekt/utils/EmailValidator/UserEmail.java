package com.example.aiprojekt.utils.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserEmailValidator.class)
public @interface UserEmail {

    String message() default "player.email.isBusy";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
