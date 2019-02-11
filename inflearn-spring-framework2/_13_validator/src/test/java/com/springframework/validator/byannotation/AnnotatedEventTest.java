package com.springframework.validator.byannotation;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;

import static com.springframework.validator.EventErrorCode.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class AnnotatedEventTest {

    private AnnotatedEvent event;

    @Autowired
    private Validator validator;

    private Errors errors;

    @Before
    public void setUp() throws Exception {
        event = new AnnotatedEvent();
    }

    @Test
    public void defaultValidatorClass() {
        assertThat(validator, instanceOf(LocalValidatorFactoryBean.class));
    }

    @Test
    public void validateNullEvent() {

        errors = getErrors(event);
        validator.validate( event, errors );

        assertTrue( errors.hasErrors() );
        assertTrue( hasNullId() );
        assertTrue( hasNullOrEmptyTitle() );
    }

    @Test
    public void validateNegativeId() {

        event.setId(-1);
        event.setTitle("Sample Event");

        errors = getErrors(event);
        validator.validate( event, errors );

        assertTrue( errors.hasErrors() );
        assertTrue( hasLessThanMinimumId() );
        assertNull( errors.getFieldError("title") );
    }

    @Test
    public void validateNullTitle() {

        event.setId(100);
        event.setTitle(null);

        errors = getErrors(event);
        validator.validate( event, errors );

        assertTrue( errors.hasErrors() );
        assertNull( errors.getFieldError("id") );
        assertTrue( hasNullOrEmptyTitle() );
    }

    @Test
    public void validateEmptyTitle() {

        event.setId(100);
        event.setTitle("");

        errors = getErrors(event);
        validator.validate( event, errors );

        assertTrue( errors.hasErrors() );
        assertNull( errors.getFieldError("id") );
        assertTrue(hasEmptyTitle());
    }

    @Test
    public void validateNormalEvent() {

        event.setId(100);
        event.setTitle("Sample Event");

        errors = getErrors(event);
        validator.validate( event, errors );

        assertFalse( errors.hasErrors() );
        assertNull( errors.getFieldError("id") );
        assertNull( errors.getFieldError("title") );
    }

    private Errors getErrors(AnnotatedEvent event) {
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

    private boolean hasNullOrEmptyTitle() {
        return hasNullTitle() || hasEmptyTitle();
    }

    private boolean hasNullTitle() {
        return Arrays.toString(errors.getFieldError("title").getCodes()).contains(TITLE_NOT_NULL);
    }

    private boolean hasEmptyTitle() {
        return Arrays.toString(errors.getFieldError("title").getCodes()).contains(TITLE_NOT_EMPTY);
    }
}