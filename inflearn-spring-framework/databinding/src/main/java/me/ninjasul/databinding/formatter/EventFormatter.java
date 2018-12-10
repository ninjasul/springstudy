package me.ninjasul.databinding.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

// Formatter는 빈으로 등록하거나 WebMvcConfigurer 를 구현하여 등록할 수 있음.
@Component
public class EventFormatter implements Formatter<FormatterEvent> {

    @Override
    public FormatterEvent parse(String text, Locale locale) throws ParseException {
        return new FormatterEvent(Integer.parseInt(text));
    }

    @Override
    public String print(FormatterEvent object, Locale locale) {
        return object.getId().toString();
    }
}