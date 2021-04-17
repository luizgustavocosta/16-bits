package com.bits16.tech.annotation.validators;

import com.bits16.tech.annotation.type.MinLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class MinLengthValidator implements ConstraintValidator<MinLength, String> {

    private int minLength;

    @Override
    public void initialize(MinLength annotation) {
        this.minLength = annotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (isNull(value)) {
            return false;
        } else {
            return value.length() >= minLength;
        }
    }
}
