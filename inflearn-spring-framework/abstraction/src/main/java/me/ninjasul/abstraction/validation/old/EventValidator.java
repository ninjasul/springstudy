package me.ninjasul.abstraction.validation.old;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //ValidationUtils.rejectIfEmptyOrWhitespace( errors, "title", "notempty", "An empty title is not allowed.");

        // ValidationUtils 없이 아래와 같이 직접 코드를 작성하여 검증을 할 수도 있음.
        Event event = (Event)target;
        String title = event.getTitle();

        if( title == null || "".equals(title) ) {
            errors.rejectValue( "title", "notempty", "An empty title is not allowed." );
        }

    }
}