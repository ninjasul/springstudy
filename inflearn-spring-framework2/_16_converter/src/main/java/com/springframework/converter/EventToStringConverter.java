package com.springframework.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventToStringConverter implements Converter<Event, String> {

    @Override
    public String convert(Event source) {
        return source.getId().toString();
    }
}