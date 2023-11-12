package com.wob.reportsystem.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PositiveDecimalValidator implements ConstraintValidator<PositiveDecimal, Double> {

    @Override
    public void initialize(PositiveDecimal constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        BigDecimal decimalValue = BigDecimal.valueOf(value);

        return decimalValue.compareTo(BigDecimal.ZERO) > 0 && decimalValue.scale() <= 2;
    }
}

