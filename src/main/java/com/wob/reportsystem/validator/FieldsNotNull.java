package com.wob.reportsystem.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldsNotNullValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsNotNull {
    String[] fields();
    String message() default "Fields cannot be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

