package me.ninjasul.restapi.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDto eventDto, Errors errors) {
        if( eventDto.getBasePrice() > eventDto.getMaxPrice() &&
            eventDto.getMaxPrice() > 0 ) {
            errors.rejectValue( "basePrice", "wrongValue", "BasePrice is wrong");
            errors.rejectValue( "maxPrice", "wrongValue", "MaxPrice is wrong");
        }

        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();

        if( endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
            endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime()) ||
            endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ) {
            errors.rejectValue("endEventDateTime", "wrongValue", "EndEventDateTime is wrong");
        }

        LocalDateTime beginEventDateTime = eventDto.getBeginEventDateTime();

        if( beginEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime()) ||
            beginEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ) {
            errors.rejectValue("beginEventDateTime", "wrongValue", "BeginEventDateTime is wrong");
        }

        if( eventDto.getBeginEnrollmentDateTime().isBefore(eventDto.getCloseEnrollmentDateTime()) ) {
            errors.rejectValue("beginEnrollmentDateTime", "wrongValue", "BeginEnrollmentDateTime is wrong");
            errors.rejectValue("closeEnrollmentDateTime", "wrongValue", "CloseEnrollmentDateTime is wrong");
        }

    }
}