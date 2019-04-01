package com.ojdgaf.examples.springapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartsWithValidator implements ConstraintValidator<StartsWith, String> {
    private String prefix;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s.startsWith(prefix);
    }

    @Override
    public void initialize(StartsWith constraintAnnotation) {
        prefix = constraintAnnotation.value();
    }
}
