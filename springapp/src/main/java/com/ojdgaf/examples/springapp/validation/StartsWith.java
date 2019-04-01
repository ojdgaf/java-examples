package com.ojdgaf.examples.springapp.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy=StartsWithValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartsWith {
    public String value() default "";

    public String message() default "String prefix is wrong";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
