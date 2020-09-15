package com.cloud.error.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class AuthorValidator implements ConstraintValidator<Author, String> {

    List<String> authors = Arrays.asList("Richard Bejtlich", "Antonio Salas", "Mónica Valle", "Nassim Nicholas Taleb");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return authors.contains(value);

    }

}
