package com.example.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;



@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp="^\\+?[a-z0-9](([-+.]|[_]+)?[a-z0-9]+)*@([a-z0-9]+(\\.|\\-))+[a-z]{2,6}$",message="{validation.email}")
@Constraint(validatedBy={})
public @interface Email {
    String message() default "{validation.email}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
