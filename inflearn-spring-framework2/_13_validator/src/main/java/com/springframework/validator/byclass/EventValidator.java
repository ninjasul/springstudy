package com.springframework.validator.byclass;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.springframework.validator.EventErrorCode.*;

public class EventValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Event.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Event event = (Event)target;

        if( event.getId() == null ) {
            errors.rejectValue("id", ID_NOT_NULL, "id should not be null");
        }
        else if( event.getId() <= 0 ) {
            errors.rejectValue("id", ID_MIN, "id should be equal to or greater than zero");
        }

        ValidationUtils.rejectIfEmpty( errors, "title", TITLE_NOT_EMPTY, "title should not be empty");
    }
}