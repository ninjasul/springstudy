package com.springframework.validator.byclass;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

import static com.springframework.validator.EventErrorCode.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class EventValidatorTest {

    private Event event;

    private Validator validator;

    private Errors errors;

    @Before
    public void setUp() throws Exception {
        event = new Event();
        validator = new EventValidator();
    }

    @Test
    public void validateNullEvent() {

        errors = getErrors(event);
        validator.validate(event, errors);

        assertTrue(errors.hasErrors());
        assertTrue( hasNullId() );
        assertTrue( hasEmptyTitle() );
    }

    @Test
    public void validateNegativeId() {

        event.setId(-1);
        event.setTitle("Sample Event");

        errors = getErrors(event);
        validator.validate(event, errors);

        assertTrue(errors.hasErrors());
        log.info(errors.toString());

        assertTrue( hasLessThanMinimumId() );
        assertNull(errors.getFieldError("title"));
    }

    @Test
    public void validateNullTitle() {

        event.setId(100);

        errors = getErrors(event);
        validator.validate(event, errors);

        assertTrue(errors.hasErrors());
        assertNull(errors.getFieldError("id"));
        assertTrue( hasEmptyTitle() );
    }

    @Test
    public void validateEmptyTitle() {

        event.setId(100);
        event.setTitle("");

        errors = getErrors(event);
        validator.validate(event, errors);

        assertTrue(errors.hasErrors());
        assertNull(errors.getFieldError("id"));
        assertTrue( hasEmptyTitle() );
    }

    @Test
    public void validateNormalEvent() {

        event.setId(100);
        event.setTitle("Sample Event");

        errors = getErrors(event);
        validator.validate(event, errors);

        assertFalse(errors.hasErrors());
        assertNull(errors.getFieldError("id"));
        assertNull(errors.getFieldError("title"));
    }

    private Errors getErrors(Event event) {
        errors = new BeanPropertyBindingResult(event, "event");
        assertTrue(validator.supports(event.getClass()));
        return errors;
    }

    private boolean hasNullId() {
        return Arrays.toString(errors.getFieldError("id").getCodes()).contains(ID_NOT_NULL);
    }

    private boolean hasLessThanMinimumId() {
        return Arrays.toString(errors.getFieldError("id").getCodes()).contains(ID_MIN);
    }

    private boolean hasNullTitle() {
        return Arrays.toString(errors.getFieldError("title").getCodes()).contains(TITLE_NOT_NULL);
    }

    private boolean hasEmptyTitle() {
        return Arrays.toString(errors.getFieldError("title").getCodes()).contains(TITLE_NOT_EMPTY);
    }
}