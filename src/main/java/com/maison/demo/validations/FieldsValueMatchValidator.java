package com.maison.demo.validations;

import com.maison.demo.annotations.FieldsValueMatch;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        System.out.println("match => " + fieldValue + ":" + fieldMatchValue);

        // if (fieldValue != null) {
        //     // FIXME: fix password always do not match when using passwordEncoder
        //     // for encoding password before storing into database
        //     if (fieldValue.toString().startsWith("$2a")) {
        //         return true;
        //     }
        //     return fieldValue.equals(fieldMatchValue);
        // }
        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        }
        return fieldMatchValue == null;
    }
}
