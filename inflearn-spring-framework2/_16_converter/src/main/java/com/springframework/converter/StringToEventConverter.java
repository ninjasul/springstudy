package com.springframework.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEventConverter implements Converter<String, Event> {

    @Override
    public Event convert(String source) {
        return new Event(Integer.parseInt(source));
    }
}