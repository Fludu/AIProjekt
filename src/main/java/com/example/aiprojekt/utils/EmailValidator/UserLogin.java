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
@Constraint(validatedBy = UserLoginValidator.class)
public @interface UserLogin {

    String message() default "player.login.isBusy";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
