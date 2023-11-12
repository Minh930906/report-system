package com.wob.reportsystem.validator;

import com.wob.reportsystem.dto.ListingDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class FieldsNotNullValidator implements ConstraintValidator<FieldsNotNull, ListingDTO> {
    private String[] fields;

    @Override
    public void initialize(FieldsNotNull constraint) {
        fields = constraint.fields();
    }

    @Override
    public boolean isValid(ListingDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        for (String fieldName : fields) {
            try {
                Field field = value.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(value);
                if (fieldName.equals("uploadTime")){
                    if (fieldValue==null){
                        return false;
                    }
                }
                if (fieldValue == null) {
                    return false;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return false;
            }
        }

        return true;
    }
}
