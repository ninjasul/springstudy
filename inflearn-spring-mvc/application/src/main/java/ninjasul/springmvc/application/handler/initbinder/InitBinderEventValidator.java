package ninjasul.springmvc.application.handler.initbinder;

import ninjasul.springmvc.application.Event;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class InitBinderEventValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event)target;

        if( event.getLimit().intValue() < 0 ) {
            errors.rejectValue("limit", "wrongValue", "The negative value is not allowed." );
        }
    }
}