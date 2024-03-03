package com.naveen.mvcusingthymleaf.customannotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String prefix;

    @Override
    public void initialize(CourseCode courseCode) {
        this.prefix = courseCode.value();
        ConstraintValidator.super.initialize(courseCode);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return true;
        if (s.isEmpty()) return true;
        return s.startsWith(prefix);
    }
}
